package luj.persist.internal.data.field;

import java.io.IOException;
import luj.persist.internal.database.save.json.JsonWriter;

public interface DataField {

  void writeJson(JsonWriter writer, String key) throws IOException;

  FieldOp<DataField> getFieldOp();
}
