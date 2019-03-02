package luj.persist.example.module.login.control;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Collection;
import luj.data.type.JSet;
import luj.data.type.impl.Data;
import luj.persist.data.impl.DataImplGetter;
import luj.persist.data.root.DataRoot;
import luj.persist.database.load.DbDataLoader;
import luj.persist.database.load.DbSetLoader;
import luj.persist.database.save.TransactionSaver;
import luj.persist.example.core.DataOp;
import luj.persist.example.core.GameDbConnector;
import luj.persist.example.core.GameDbIdTool;
import luj.persist.example.module.player.database.PlayerDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRegisterer {

  public void register(String account) {
    HikariDataSource source = _gameDbConnector.connect();
    JSet playerSet = _dbSetLoader.load(source, PlayerDb.class, account);

    DataOp op = _dataOp;
    if (!op.isEmpty(playerSet)) {
      LOG.debug("已经注册过了：{}", account);

      Collection<PlayerDb> player = _dbDataLoader.load(source, playerSet);
      PlayerDb playerDb = player.stream().findFirst().orElseThrow(RuntimeException::new);

      op.set(playerDb.account(), account);

      DataRoot root = _dataImplGetter.get((Data) playerDb);
      root.getRootOp().applyModification(root, playerDb);

      _dbSaver.save(source, playerDb);

      return;
    }

    LOG.info("新玩家进行注册 -> {}", account);

    Long playerId = _gameDbIdTool.nextId();
    PlayerDb playerDb = op.create(PlayerDb.class, playerId);

    op.set(playerDb.account(), account);
    op.add(playerSet, playerId);

    _dbSaver.save(source, playerSet, playerDb);
  }

  private static final Logger LOG = LoggerFactory.getLogger(LoginRegisterer.class);

  @Autowired
  private GameDbIdTool _gameDbIdTool;

  @Autowired
  private GameDbConnector _gameDbConnector;

  @Autowired
  private DbSetLoader _dbSetLoader;

  @Autowired
  private DataOp _dataOp;

  @Autowired
  private DbDataLoader.Adapter _dbDataLoader;

  @Autowired
  private TransactionSaver.Adapter _dbSaver;

  @Autowired
  private DataImplGetter _dataImplGetter;
}
