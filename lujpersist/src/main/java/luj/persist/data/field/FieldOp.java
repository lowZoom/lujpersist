package luj.persist.data.field;

import java.io.IOException;
import luj.data.type.impl.Data;
import luj.persist.database.load.json.JsonReader;

public interface FieldOp<F extends DataField> {

  Class<? extends Data> getDataType();

  void initField(Data fieldData);

  boolean isDirty(F fieldImpl);

  void applyModification(F fieldImpl);

  void readJson(F fieldImpl, JsonReader reader) throws IOException;
}
