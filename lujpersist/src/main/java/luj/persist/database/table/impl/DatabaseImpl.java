package luj.persist.database.table.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import luj.persist.database.jdbc.JdbcQueryRunner;
import luj.persist.database.jdbc.JdbcUpdateRunner;
import luj.persist.database.table.TabRecoverDatabase;
import luj.persist.database.table.scan.TableValidateDatabase;

class DatabaseImpl implements TabRecoverDatabase, TableValidateDatabase {

  public DatabaseImpl(DataSource dataSource, JdbcUpdateRunner jdbcUpdateRunner,
      JdbcQueryRunner jdbcQueryRunner) {
    _dataSource = dataSource;

    _jdbcUpdateRunner = jdbcUpdateRunner;
    _jdbcQueryRunner = jdbcQueryRunner;
  }

  @Override
  public void runUpdate(String sql) throws SQLException {
    _jdbcUpdateRunner.runUncaught(_dataSource, sql);
  }

  @Override
  public void runQuery(String sql) throws SQLException {
    _jdbcQueryRunner.runUncaught(_dataSource, sql, this::onResult, null);
  }

  private <L> void onResult(ResultSet resultSet, L l) {

  }

  private final DataSource _dataSource;

  private final JdbcUpdateRunner _jdbcUpdateRunner;
  private final JdbcQueryRunner _jdbcQueryRunner;
}
