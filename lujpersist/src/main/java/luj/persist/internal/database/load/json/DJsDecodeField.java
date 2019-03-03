package luj.persist.internal.database.load.json;

import java.io.IOException;

public interface DJsDecodeField {

  String getFieldName();

  void readJson(JsonReader reader) throws IOException;
}
