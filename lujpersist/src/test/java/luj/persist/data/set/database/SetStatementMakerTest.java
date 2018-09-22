package luj.persist.data.set.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import luj.persist.data.root.statement.set.SStatMakeSet;
import luj.persist.data.root.statement.set.SetStatementMaker;
import luj.persist.database.save.StatementParamMock;
import luj.persist.database.save.TransSaveStatement;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class SetStatementMakerTest extends InjectTest {

  @SUT
  SetStatementMaker _sut;

  SStatMakeSet _set;

  @Before
  public void setUp() {
    _set = mock(SStatMakeSet.class);

    when(_set.getTableName()).thenReturn("table");
    when(_set.getKeyColumn()).thenReturn("key");
    when(_set.getValColumn()).thenReturn("val");
  }

  @Test
  public void make_增() {
    //-- Arrange --//
    when(_set.getKey()).thenReturn("aaa");
    when(_set.getAddHistory()).thenReturn(ImmutableSet.of(666L));

    //-- Act --//
    List<TransSaveStatement> result = make();

    //-- Assert --//
    assertThat(statement(result)).isEqualTo(new Object[][]{
        {"INSERT INTO table (key,val) VALUES (?,?)", "aaa", 666L},
    });
  }

  @Test
  public void make_删() {
    //-- Arrange --//
    when(_set.getKey()).thenReturn("ddd");
    when(_set.getRemoveHistory()).thenReturn(ImmutableSet.of(777L));

    //-- Act --//
    List<TransSaveStatement> result = make();

    //-- Assert --//
    assertThat(statement(result)).isEqualTo(new Object[][]{
        {"DELETE FROM table WHERE key=? AND val=?", "ddd", 777L},
    });
  }

  List<TransSaveStatement> make() {
    return _sut.make(_set);
  }

  Object[] statement(List<TransSaveStatement> result) {
    StatementParamMock param = new StatementParamMock();
    return result.stream()
        .map(s -> ImmutableList.builder()
            .add(s.getSql())
            .add(param.apply(s))
            .build().toArray())
        .toArray();
  }
}
