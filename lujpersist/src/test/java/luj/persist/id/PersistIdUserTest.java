package luj.persist.id;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import luj.persist.id.clock.PersistClockMock;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class PersistIdUserTest extends InjectTest {

  @SUT
  PersistIdUser _sut;

  IdGenerateState _idGenerateState;
  PersistClockMock _persistClockMock;

  @Before
  public void setUp() {
    _idGenerateState = new IdGenerateState();
    _persistClockMock = new PersistClockMock();
  }

  @Test
  public void use_单个() {
    //-- Arrange --//
    _idGenerateState.setSequence(0x0FFF);
    _persistClockMock.setNow(0x0A);

    //-- Act --//
    Long result = use();

    //-- Assert --//
    assertThat(result).isEqualTo(0x0FFF01);
    assertThat(_idGenerateState.getSequence()).isEqualTo(0x1000);
  }

  @Test
  public void use_并发多个应不重复() throws InterruptedException {
    //-- Arrange --//
    _idGenerateState.setSequence(0x0100);
    _persistClockMock.setNow(0x01);

    ExecutorService exec = Executors.newCachedThreadPool();
    Set<Long> result = Collections.synchronizedSet(new HashSet<>());

    //-- Act --//
    for (int i = 0; i < 5000; i++) {
      exec.execute(() -> result.add(use()));
    }
    exec.shutdown();
    exec.awaitTermination(1, TimeUnit.SECONDS);

    //-- Assert --//
    assertThat(result.size()).isEqualTo(5000);
  }

  Long use() {
    return _sut.use(_idGenerateState, 1, _persistClockMock);
  }
}
