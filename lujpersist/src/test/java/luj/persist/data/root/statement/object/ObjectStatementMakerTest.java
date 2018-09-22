package luj.persist.data.root.statement.object;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.persist.database.save.StatementParamMock;
import luj.persist.database.save.TransSaveStatement;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class ObjectStatementMakerTest extends InjectTest {

  @SUT
  ObjectStatementMaker _sut;

  OStatMakeObject _object;

  @Before
  public void setUp() {
    _object = mock(OStatMakeObject.class);
    when(_object.getTableName()).thenReturn("table");

    when(_object.getIdColumn()).thenReturn("id");
    when(_object.getValColumn()).thenReturn("val");
  }

  @Test
  public void make_() {
    //-- Arrange --//
    when(_object.getDataId()).thenReturn(666L);
    when(_object.isInDatabase()).thenReturn(false);
    when(_object.toJson()).thenReturn("{json}");

    //-- Act --//
    List<TransSaveStatement> result = make();

    //-- Assert --//
    assertThat(statement(result)).isEqualTo(new Object[][]{
        {"INSERT INTO table (id,val) VALUES (?,'{}')", 666L},
        {"UPDATE table SET val=? WHERE id=?", "{json}", 666L},
    });
  }

  List<TransSaveStatement> make() {
    return _sut.make(_object);
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
