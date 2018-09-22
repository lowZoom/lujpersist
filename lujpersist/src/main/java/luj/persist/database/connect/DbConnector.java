package luj.persist.database.connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;

@Service
public class DbConnector {

  public HikariDataSource connect(String url) {
    HikariConfig cfg = new HikariConfig();
    cfg.setJdbcUrl(url);

    HikariDataSource source = new HikariDataSource(cfg);
    //Connection conn = source.getConnection();

    return source;
  }
}
