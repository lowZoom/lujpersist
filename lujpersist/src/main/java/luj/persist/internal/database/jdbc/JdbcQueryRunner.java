package luj.persist.internal.database.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JdbcQueryRunner {

  @FunctionalInterface
  public interface Listener<L> {

    void onResult(ResultSet sqlResult, L listening) throws SQLException;
  }

  public <L> void run(DataSource source, String sql, Listener<L> listener, L listening) {
    try {
      runUncaught(source, sql, listener, listening);

    } catch (SQLException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  public <L> void runUncaught(DataSource source, String sql,
      Listener<L> listener, L listening) throws SQLException {
    LOG.info(sql);

    try (Connection conn = source.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql)) {

      while (result.next()) {
        listener.onResult(result, listening);
      }
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(JdbcQueryRunner.class);
}
