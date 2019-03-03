package luj.persist.internal.database.save.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import luj.persist.internal.database.save.TransSaveDatabase;
import luj.persist.internal.database.save.TransSaveTransaction;

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
