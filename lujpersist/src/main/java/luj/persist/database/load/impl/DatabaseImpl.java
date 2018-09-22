package luj.persist.database.load.impl;

import com.google.common.collect.ImmutableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import luj.persist.database.jdbc.JdbcQueryRunner;
import luj.persist.database.load.DDatLoadDatabase;

class DatabaseImpl implements DDatLoadDatabase {

  public DatabaseImpl(DataSource dataSource, JdbcQueryRunner jdbcQueryRunner) {
    _dataSource = dataSource;
    _jdbcQueryRunner = jdbcQueryRunner;
  }

  @Override
  public List<QueryResult> runQuery(String sql) {
    ImmutableList.Builder<QueryResult> result = ImmutableList.builder();
    _jdbcQueryRunner.run(_dataSource, sql, this::onResult, result);
    return result.build();
  }

  private void onResult(ResultSet sqlResult,
      ImmutableList.Builder<QueryResult> output) throws SQLException {
    Long dataId = sqlResult.getLong(1);
    String jsonStr = sqlResult.getString(2);

    QueryResultImpl result = new QueryResultImpl(dataId, jsonStr);
    output.add(result);
  }

  private final DataSource _dataSource;

  private final JdbcQueryRunner _jdbcQueryRunner;
}
