package luj.persist.database.save.json;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;

class JsonWriterJackson implements JsonWriter {

  public JsonWriterJackson(JsonGenerator generator) {
    _generator = generator;
  }

  @Override
  public void writeString(String key, String value) throws IOException {
    _generator.writeStringField(key, value);
  }

  private final JsonGenerator _generator;
}
