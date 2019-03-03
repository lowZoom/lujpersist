package luj.persist.internal.data.meta.property;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Test;

public class PropertyListMakerTest extends InjectTest {

  @SUT
  PropertyListMaker _sut;

  Class<?> _dataType;

  @Test
  public void make_() {
    //-- Arrange --//
    _dataType = TestDb.class;

    //-- Act --//
    List<BeanProperty> result = make();

    //-- Assert --//
    assertThat(prop(result)).isEqualTo(new Object[][]{
        {"a", "aTest"},
    });
  }

  List<BeanProperty> make() {
    return _sut.make(_dataType);
  }

  Object[] prop(List<BeanProperty> result) {
    return result.stream()
        .map(p -> new Object[]{p.getName(), p.getGetter().apply(new TestDb())})
        .toArray();
  }
}
