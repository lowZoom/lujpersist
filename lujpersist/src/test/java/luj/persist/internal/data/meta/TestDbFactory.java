package luj.persist.internal.data.meta;

import luj.persist.internal.data.object.DataFactory;
import org.springframework.stereotype.Component;

@Component
class TestDbFactory implements DataFactory<TestDb> {

  @Override
  public TestDb create() {
    return new TestDb();
  }
}
