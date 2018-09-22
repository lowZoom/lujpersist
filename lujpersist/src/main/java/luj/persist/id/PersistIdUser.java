package luj.persist.id;

import java.util.concurrent.locks.Lock;
import luj.persist.id.clock.PersistIdClock;
import org.springframework.stereotype.Service;

@Service
public class PersistIdUser {

  public Long use(IdGenerateState state, long machineId, PersistIdClock clock) {
    long curSeq = consumeSeq(state);
    long timestamp = curSeq >>> TIMESTAMP_SHIFT;

    Long resulId = ((curSeq << TIME_SEQ_SHIFT) & TIME_SEQ_MASK) | machineId;
    return waitUsable(clock, timestamp, resulId);
  }

  private long consumeSeq(IdGenerateState state) {
    Lock lock = state.getGenerateLock();
    lock.lock();
    try {
      long curSeq = state.getSequence();

      long nextSeq = curSeq + 1;
      state.setSequence(nextSeq);

      return curSeq;

    } finally {
      lock.unlock();
    }
  }

  private Long waitUsable(PersistIdClock clock, long time, Long result) {
    while (clock.now() < time) {
      // NOOP
    }
    return result;
  }

  private static final long TIMESTAMP_SHIFT = IdBitConst.SEQ_BITS;

  private static final long TIME_SEQ_SHIFT = IdBitConst.MACHINE_BITS;
  private static final long TIME_SEQ_MASK = -1 << TIME_SEQ_SHIFT;
}
