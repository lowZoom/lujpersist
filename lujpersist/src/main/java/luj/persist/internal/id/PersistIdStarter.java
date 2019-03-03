package luj.persist.internal.id;

import luj.persist.internal.id.clock.PersistIdClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistIdStarter {

  public IdGenerateState start() {
    long now = _persistIdClock.now();
    long startSeq = now << IdBitConst.SEQ_BITS;

    IdGenerateState state = new IdGenerateState();
    state.setSequence(startSeq);

    return state;
  }

  @Autowired
  private PersistIdClock _persistIdClock;
}
