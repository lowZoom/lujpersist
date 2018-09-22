package luj.persist.id.clock;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

@Service
public class PersistIdClockImpl implements PersistIdClock {

  @Override
  public long now() {
    return System.currentTimeMillis() + EPOCH_OFFSET;
  }

  /**
   * 2000年1月1日 整点
   */
  private static final long EPOCH_OFFSET = -LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0)
      .atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
}
