package luj.persist.internal.database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JdbcUpdateRunner {

  public void runUncaught(DataSource source, String sql) throws SQLException {
    LOG.info(sql);

    try (Connection conn = source.getConnection();
        Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sql);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(JdbcUpdateRunner.class);
}
