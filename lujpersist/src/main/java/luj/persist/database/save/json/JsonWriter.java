package luj.persist.database.save.json;

import java.io.IOException;

public interface JsonWriter {

  void writeString(String key, String value) throws IOException;
}
