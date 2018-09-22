package luj.persist.database.save.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import luj.persist.database.save.TransSaveDatabase;
import luj.persist.database.save.TransSaveTransaction;

class DatabaseImpl implements TransSaveDatabase {

  public DatabaseImpl(DataSource dataSource) {
    _dataSource = dataSource;
  }

  @Override
  public TransSaveTransaction startTransaction() {
    try {
      Connection conn = _dataSource.getConnection();
      conn.setAutoCommit(false);
      return new TransactionImpl(conn);

    } catch (SQLException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final DataSource _dataSource;
}
