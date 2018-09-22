package luj.persist.data.meta;

import static org.assertj.core.api.Assertions.assertThat;

import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Test;

public class DbMetaMakerTest extends InjectTest {

  @SUT
  DbMetaMaker _sut;

  Class<?> _dataType;

  @Test
  public void make_() {
    //-- Arrange --//
    _dataType = TestDb.class;

    //-- Act --//
    DbMeta result = make();

    //-- Assert --//
    assertThat(result.getDataType()).isSameAs(TestDb.class);
    assertThat(result.getDataAnno()).isNotNull();
    assertThat(result.getDataTableName()).isEqualTo("TestDb");
    assertThat(result.getSetTableName()).isEqualTo("TestDbSet");
  }

  DbMeta make() {
    return _sut.make(_dataType, null);
  }
}
