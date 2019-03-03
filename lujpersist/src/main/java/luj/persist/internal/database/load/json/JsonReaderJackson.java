package luj.persist.internal.database.load.json;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;

class JsonReaderJackson implements JsonReader {

  public JsonReaderJackson(JsonParser parser) {
    _parser = parser;
  }

  @Override
  public String readStr() throws IOException {
    return _parser.getText();
  }

  @Override
  public int readInt() throws IOException {
    return _parser.getIntValue();
  }

  private final JsonParser _parser;
}
