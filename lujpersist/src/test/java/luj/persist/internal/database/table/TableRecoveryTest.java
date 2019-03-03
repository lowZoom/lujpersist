package luj.persist.internal.database.table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class TableRecoveryTest extends InjectTest {

  @SUT
  TableRecovery _sut;

  DatabaseMock _database;

  TableMock _tableOk;
  TableMock _tableError;

  @Before
  public void setUp() {
    _database = new DatabaseMock();

    _tableOk = new TableMock();
    _tableError = new TableMock();
  }

  @Test
  public void recover_() {
    //-- Arrange --//
    when(_tableOk.getTableName()).thenReturn("ok");
    when(_tableOk.validate()).thenReturn(true);

    when(_tableError.getTableName()).thenReturn("error");
    when(_tableError.validate()).thenReturn(false);
    when(_tableError.getColumnDef()).thenReturn("col1,col2");

    //-- Act --//
    recover();

    //-- Assert --//
    assertThat(update()).isEqualTo(new Object[]{
        "DROP TABLE error",
        "CREATE TABLE error (col1,col2)",
    });
  }

  void recover() {
    _sut.recover(_database, ImmutableList.of(_tableOk, _tableError));
  }

  Object[] update() {
    return _database.getRunHistory().toArray();
  }
}
