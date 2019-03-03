package luj.persist.internal.data.meta.property;

import static org.assertj.core.api.Assertions.assertThat;

import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Test;

public class ListBeanFinderTest extends InjectTest {

  @SUT
  ListBeanFinder _sut;

  Class<?> _dataType;

  @Test
  public void find_() {
    //-- Arrange --//
    _dataType = TestDb.class;

    //-- Act --//
    TestDbProperty result = (TestDbProperty) find();

    //-- Assert --//
    assertThat(result).isOfAnyClassIn(TestDbProperty.class);
  }

  Object find() {
    return _sut.find(_dataType);
  }
}
