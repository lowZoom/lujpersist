package luj.persist.internal.database.save;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import luj.persist.internal.database.save.json.DJsEncodeField;
import luj.persist.internal.database.save.json.DataJsonEncoder;
import luj.persist.test.InjectTest;
import luj.test.anno.SUT;
import org.junit.Test;

public class DataJsonEncoderTest extends InjectTest {

  @SUT
  DataJsonEncoder _sut;

  List<DJsEncodeField> _fieldList;

  @Test
  public void encode_() throws IOException {
    //-- Arrange --//
    _fieldList = ImmutableList.of(
        new FieldStrMock("str", "val")
    );

    //-- Act --//
    String result = encode();

    //-- Assert --//
    assertThat(map(result)).containsExactly(
        entry("str", "val")
    );
  }

  String encode() {
    return _sut.encode(_fieldList);
  }

  Map<String, Object> map(String result) throws IOException {
    return new ObjectMapper().readValue(result, new JsonMap());
  }

  static class JsonMap extends TypeReference<Map<String, Object>> {
    // NOOP
  }
}
