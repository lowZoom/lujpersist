package luj.persist.internal.database.table.scan;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import luj.persist.internal.database.table.sql.SqlErrorMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableValidator {

  public boolean validate(TableValidateDatabase database, TableValidateTable table) {
    String tableName = table.getTableName();
    String selectColumns = table.getSelectColumns();
    String sql = makeSql(tableName, selectColumns);

    Set<String> errorSet = new HashSet<>();
    try {
      //errorSet.add(ScanResult.EMPTY);
      database.runQuery(sql);

    } catch (SQLException e) {
      handleException(e, errorSet);
    }

    for (String error : errorSet) {
      LOG.warn(error);
    }

    return errorSet.isEmpty();
  }

  private String makeSql(String tableName, String columns) {
    return "SELECT " + columns + " FROM " + tableName;
  }

  private void handleException(SQLException e, Set<String> errorSet) {
    SqlErrorMatcher m = _sqlErrorMatcher;
    if (m.match(e, "no", "table")) {
      errorSet.add("<不存在>" + e.getMessage());
      return;
    }

    if (m.match(e, "no", "column")) {
      errorSet.add("<缺列>" + e.getMessage());
      return;
    }

    throw new UnsupportedOperationException(e);
  }

  private static final Logger LOG = LoggerFactory.getLogger(TableValidator.class);

  @Autowired
  private SqlErrorMatcher _sqlErrorMatcher;
}
