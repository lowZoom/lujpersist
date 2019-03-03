package luj.persist.internal.database.save;

import java.io.IOException;
import luj.persist.internal.database.save.json.DJsEncodeField;
import luj.persist.internal.database.save.json.JsonWriter;

class FieldStrMock implements DJsEncodeField {

  public FieldStrMock(String key, String value) {
    _key = key;
    _value = value;
  }

  @Override
  public void writeToJson(JsonWriter writer) throws IOException {
    writer.writeString(_key, _value);
  }

  private final String _key;
  private final String _value;
}
