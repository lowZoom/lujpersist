package luj.persist.internal.database.load.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import luj.persist.internal.database.json.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 将数据库加载出来的json字符串，转化为内存中数据对象
 */
@Service
public class DataJsonDecoder {

  public Object decode(DJsDecodeJson json) {
    DJsDecodeObject dataObj = json.createDataObject();
    List<DJsDecodeField> fieldList = dataObj.getFieldList();

    String jsonStr = json.getJsonStr();
    try (JsonParser parser = _jacksonFactory.createParser(jsonStr)) {
      JsonReader reader = new JsonReaderJackson(parser);

      // 尝试将json中的字段，读取到对象中对应字段
      while (parser.nextToken() != JsonToken.END_OBJECT) {
        tryParseField(parser, reader, fieldList);
      }

      return dataObj.getData();

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void tryParseField(JsonParser parser, JsonReader reader,
      List<DJsDecodeField> fieldList) throws IOException {
    String jsonKey = parser.getCurrentName();
    Optional<DJsDecodeField> field = findField(fieldList, jsonKey);

    if (!field.isPresent()) {
      return;
    }
    readField(parser, reader, field.get());
  }

  private Optional<DJsDecodeField> findField(List<DJsDecodeField> fieldList, String fieldName) {
    return fieldList.stream()
        .filter(f -> Objects.equals(f.getFieldName(), fieldName))
        .findAny();
  }

  private void readField(JsonParser parser, JsonReader reader,
      DJsDecodeField field) throws IOException {
    parser.nextToken();
    field.readJson(reader);
  }

  @Autowired
  private JacksonFactory _jacksonFactory;
}
