package luj.persist.example.core.boot;

import com.zaxxer.hikari.HikariDataSource;
import luj.persist.database.table.TableRecoveryAdapter;
import luj.persist.example.core.GameDbConnector;
import luj.persist.example.module.login.ui.LoginUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameBoot {

  public void boot() {
    initDatabase();

    _loginUi.show();
  }

  private void initDatabase() {
    HikariDataSource source = _gameDbConnector.connect();

    try {
      Thread.sleep(500);

    } catch (InterruptedException e) {
      throw new UnsupportedOperationException(e);
    }

    _dbTableRecovery.recover(source);
  }

  @Autowired
  private GameDbConnector _gameDbConnector;

  @Autowired
  private TableRecoveryAdapter _dbTableRecovery;

  @Autowired
  private LoginUi _loginUi;
}
