package luj.persist.internal.id.clock;

public class PersistClockMock implements PersistIdClock {

  public void setNow(long now) {
    _offset = now - System.currentTimeMillis();
  }

  @Override
  public long now() {
    return System.currentTimeMillis() + _offset;
  }

  private long _offset;
}
