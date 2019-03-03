package luj.persist.internal.data.meta;

import static org.assertj.core.api.Assertions.assertThat;

import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Test;

public class DbMetaHolderTest extends InjectTest {

  @SUT
  TestDbMeta _sut;

  @Test
  public void init_() {
    //-- Arrange --//

    //-- Act --//
    DbMeta result = _sut.getMeta();

    //-- Assert --//
    assertThat(result.getDataType()).isSameAs(TestDb.class);
    assertThat(result.getDataTableName()).isEqualTo("TestDb");
    assertThat(result.getDataFactory()).isOfAnyClassIn(TestDbFactory.class);
    assertThat(result.getPropertyList()).isNotNull();
  }
}
