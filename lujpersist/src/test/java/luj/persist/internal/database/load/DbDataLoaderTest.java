package luj.persist.internal.database.load;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class DbDataLoaderTest extends InjectTest {

  @SUT
  DbDataLoader _sut;

  DDatLoadDatabaseMock _database;
  DDatLoadSet _set;

  @Before
  public void setUp() {
    _database = new DDatLoadDatabaseMock();
    _set = _database;
  }

  @Test
  public void load_() {
    //-- Arrange --//
    when(_set.getDataValueColumn()).thenReturn("val");
    when(_set.getDataTableName()).thenReturn("table");

    when(_set.getDataIdColumn()).thenReturn("id");
    when(_set.iter()).thenReturn(ImmutableList.of(11L, 22L));

    //-- Act --//
    Collection<String> result = load();

    //-- Assert --//
    assertThat(result).containsExactly(
        "SELECT id,val FROM table WHERE id IN (11,22)"
    );
  }

  Collection<String> load() {
    return _sut.load(_database, _set);
  }
}
