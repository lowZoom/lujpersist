package luj.persist.data.field.type.str;

import java.io.IOException;
import luj.persist.data.field.DataField;
import luj.persist.data.field.FieldOp;
import luj.persist.database.save.json.JsonWriter;

class DbStrImpl implements DataField {

  public DbStrImpl(DbStr state, FieldOp<DataField> op) {
    _dbStr = state;
    _op = op;
  }

  public DbStr getDbStr() {
    return _dbStr;
  }

  @Override
  public FieldOp<DataField> getFieldOp() {
    return _op;
  }

  @Override
  public void writeJson(JsonWriter writer, String key) throws IOException {
    //TODO: null不写到json
    writer.writeString(key, _dbStr.getValue());
  }

  private final DbStr _dbStr;

  private final FieldOp<DataField> _op;
}
