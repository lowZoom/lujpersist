package luj.persist.internal.database.save.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import luj.persist.internal.database.save.TransSaveExecutable;
import luj.persist.internal.database.save.TransSaveTransaction;

class TransactionImpl implements TransSaveTransaction {

  public TransactionImpl(Connection connection) {
    _connection = connection;
  }

  @Override
  public TransSaveExecutable startExecute(String sql) throws SQLException {
    PreparedStatement statement = _connection.prepareStatement(sql);
    return new ExecutableImpl(statement);
  }

  @Override
  public void commit() throws SQLException {
    _connection.commit();
  }

  @Override
  public void rollback() {
    try {
      _connection.rollback();

    } catch (SQLException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Override
  public void close() {
    try {
      _connection.close();

    } catch (SQLException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final Connection _connection;
}
