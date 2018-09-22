package luj.persist.database.table.sql;

import java.sql.SQLException;
import luj.persist.database.table.TabRecoverDatabase;
import luj.persist.database.table.TabRecoverTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlTableDropper {

  public void drop(TabRecoverDatabase database, TabRecoverTable table) {
    String tableName = table.getTableName();
    String sql = makeSql(tableName);

    try {
      database.runUpdate(sql);

    } catch (SQLException e) {
      handleException(e);
    }
  }

  private String makeSql(String tableName) {
    return String.format("DROP TABLE %s", tableName);
  }

  private void handleException(SQLException e) {
    if (_sqlErrorMatcher.match(e, "no", "table")) {
      return;
    }
    throw new UnsupportedOperationException(e);
  }

  @Autowired
  private SqlErrorMatcher _sqlErrorMatcher;
}
