package luj.persist.id;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IdGenerateState {

  public IdGenerateState() {
    _generateLock = new ReentrantLock();
  }

  public long getSequence() {
    return _sequence;
  }

  public void setSequence(long sequence) {
    _sequence = sequence;
  }

  public Lock getGenerateLock() {
    return _generateLock;
  }

  public void setGenerateLock(Lock generateLock) {
    _generateLock = generateLock;
  }

  private long _sequence;

  private Lock _generateLock;
}
