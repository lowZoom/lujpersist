package luj.persist.example.core;

import javax.annotation.PostConstruct;
import luj.persist.id.IdGenerateState;
import luj.persist.id.PersistIdStarter;
import luj.persist.id.PersistIdUser;
import luj.persist.id.clock.PersistIdClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDbIdTool {

  public Long nextId() {
    return _persistIdUser.use(_idGenerateState, 1, _persistIdClock);
  }

  @PostConstruct
  void init() {
    _idGenerateState = _persistIdStarter.start();
  }

  private IdGenerateState _idGenerateState;

  @Autowired
  private PersistIdStarter _persistIdStarter;

  @Autowired
  private PersistIdUser _persistIdUser;

  @Autowired
  private PersistIdClock _persistIdClock;
}
