package luj.persist.database.load.json;

import java.io.IOException;

public interface JsonReader {

  String readStr() throws IOException;

  int readInt() throws IOException;
}
