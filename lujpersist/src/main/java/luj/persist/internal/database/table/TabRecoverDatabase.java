package luj.persist.internal.database.table;

import java.sql.SQLException;

public interface TabRecoverDatabase {

  void runUpdate(String sql) throws SQLException;
}
