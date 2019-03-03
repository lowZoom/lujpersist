package luj.persist.api.anno.proc.bean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class DatabaseAllGeneratorImplTest {

  DatabaseAllGeneratorImpl.BeanType _type;
  DatabaseAllGeneratorImpl.Implementation _implementation;

  @Before
  public void setUp() throws IOException {
    _type = mock(DatabaseAllGeneratorImpl.BeanType.class);
    _implementation = mock(DatabaseAllGeneratorImpl.Implementation.class);

    when(_type.generateImplementation(any())).thenReturn(_implementation);
  }

  @Test
  public void generate_() throws IOException {
    //-- Arrange --//
    when(_type.isInterface()).thenReturn(true);
    when(_type.getClassName()).thenReturn("TestDb");

    //-- Act --//
    generate();

    //-- Assert --//
    verify(_type).generateImplementation("TestDbImpl");
    verify(_implementation).generateFactory("TestDbFactory");

    verify(_type).generatePropertyList("TestDbProperty");
    verify(_type).generateMeta("TestDbMeta");
  }

  void generate() throws IOException {
    new DatabaseAllGeneratorImpl(_type).generate();
  }
}
