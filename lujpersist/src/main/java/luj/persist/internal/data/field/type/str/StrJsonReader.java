package luj.persist.internal.data.field.type.str;

import java.io.IOException;
import luj.persist.internal.database.load.json.JsonReader;
import org.springframework.stereotype.Service;

@Service
public class StrJsonReader {

  public void read(DbStr dbStr, JsonReader reader) throws IOException {
    String fieldVal = reader.readStr();
    dbStr.setValue(fieldVal);
  }
}
