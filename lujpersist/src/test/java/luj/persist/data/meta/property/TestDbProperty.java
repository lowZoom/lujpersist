package luj.persist.data.meta.property;

import java.util.function.Function;
import luj.persist.data.object.property.DataPropertyList;

@DataPropertyList(TestDb.class)
class TestDbProperty {

  Function<TestDb, String> a() {
    return TestDb::a;
  }
}
