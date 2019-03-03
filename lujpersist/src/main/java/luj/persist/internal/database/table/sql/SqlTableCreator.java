package luj.persist.internal.database.table.sql;

import java.sql.SQLException;
import luj.persist.internal.database.table.TabRecoverDatabase;
import luj.persist.internal.database.table.TabRecoverTable;
import org.springframework.stereotype.Service;

@Service
public class SqlTableCreator {

  public void create(TabRecoverDatabase database, TabRecoverTable table) {
    String tableName = table.getTableName();
    String columnDef = table.getColumnDef();

    String sql = makeSql(tableName, columnDef);
    try {
      database.runUpdate(sql);

    } catch (SQLException e) {
      handleException(e);
    }
  }

  private String makeSql(String tableName, String columnDef) {
    return "CREATE TABLE " + tableName + " (" + columnDef + ')';
  }

  private void handleException(SQLException e) {
    if (checkExist(e.getMessage())) {
      return;
    }
    throw new UnsupportedOperationException(e);
  }

  private boolean checkExist(String message) {
    return message.contains("already") && message.contains("exist");
  }
}
