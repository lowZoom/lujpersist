package luj.persist.data.meta;

import luj.persist.data.object.DataFactory;
import org.springframework.stereotype.Component;

@Component
class TestDbFactory implements DataFactory<TestDb> {

  @Override
  public TestDb create() {
    return new TestDb();
  }
}
