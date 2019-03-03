package luj.persist.example.core;

import com.zaxxer.hikari.HikariDataSource;
import luj.persist.internal.database.connect.DbConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDbConnector {

  public HikariDataSource connect() {
    return _dbConnector.connect("jdbc:sqlite:C:/ssd/work/lujgame/lujgame.db");
  }

  @Autowired
  private DbConnector _dbConnector;
}
