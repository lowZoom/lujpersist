package luj.persist.internal.database.save.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import luj.persist.internal.database.save.TransSaveExecutable;
import luj.persist.internal.database.save.TransSaveStatParam;

class ExecutableImpl implements TransSaveExecutable, TransSaveStatParam {

  public ExecutableImpl(PreparedStatement preparedStatement) {
    _preparedStatement = preparedStatement;
  }

  @Override
  public TransSaveStatParam getParam() {
    return this;
  }

  @Override
  public void execute() throws SQLException {
    _preparedStatement.executeUpdate();
  }

  @Override
  public void close() throws SQLException {
    _preparedStatement.close();
  }

  @Override
  public void setString(int paramIndex, String x) throws SQLException {
    checkNotNull(x);
    _preparedStatement.setString(paramIndex, x);
  }

  @Override
  public void setLong(int paramIndex, Long x) throws SQLException {
    checkNotNull(x);
    _preparedStatement.setLong(paramIndex, x);
  }

  private final PreparedStatement _preparedStatement;
}
