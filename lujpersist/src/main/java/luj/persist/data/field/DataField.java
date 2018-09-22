package luj.persist.data.field;

import java.io.IOException;
import luj.persist.database.save.json.JsonWriter;

public interface DataField {

  void writeJson(JsonWriter writer, String key) throws IOException;

  FieldOp<DataField> getFieldOp();
}
