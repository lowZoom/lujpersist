package luj.persist.database.save.json;

import java.io.IOException;

public interface DJsEncodeField {

  void writeToJson(JsonWriter writer) throws IOException;
}
