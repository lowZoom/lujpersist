package luj.persist.internal.database.save;

import java.sql.SQLException;

public interface TransSaveTransaction {

  TransSaveExecutable startExecute(String sql) throws SQLException;

  void commit() throws SQLException;

  void rollback();

  void close();
}
