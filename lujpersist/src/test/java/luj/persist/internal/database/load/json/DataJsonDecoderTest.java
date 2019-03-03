package luj.persist.internal.database.load.json;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Before;
import org.junit.Test;

public class DataJsonDecoderTest extends InjectTest {

  @SUT
  DataJsonDecoder _sut;

  DJsDecodeJson _json;

  @Before
  public void setUp() {
    _json = mock(DJsDecodeJson.class);
  }

  @Test
  public void decode_() {
    //-- Arrange --//
    when(_json.getJsonStr()).thenReturn('{'
        + "\"k1\": 1,"
        + "\"k2\": \"v2\""
        + '}');

    when(_json.createDataObject()).thenReturn(new DJsDecodeObjectMock());

    //-- Act --//
    Result result = (Result) decode();

    //-- Assert --//
    assertThat(result.k1).isEqualTo(1);
    assertThat(result.k2).isEqualTo("v2");
  }

  Object decode() {
    return _sut.decode(_json);
  }

  static class Result {

    int k1;

    String k2;
  }
}
