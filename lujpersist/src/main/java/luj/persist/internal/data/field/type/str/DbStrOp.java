package luj.persist.internal.data.field.type.str;

import java.io.IOException;
import luj.data.type.JStr;
import luj.data.type.impl.Data;
import luj.persist.internal.data.field.FieldOp;
import luj.persist.internal.database.load.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DbStrOp implements FieldOp<DbStrImpl> {

  @Override
  public Class<? extends Data> getDataType() {
    return JStr.class;
  }

  @Override
  public void initField(Data fieldData) {
    _strImplInitializer.init(fieldData, this);
  }

  @Override
  public boolean isDirty(DbStrImpl fieldImpl) {
    return fieldImpl.getDbStr().getMod() != null;
  }

  @Override
  public void applyModification(DbStrImpl fieldImpl) {
    _modificationApplier.apply(fieldImpl.getDbStr());
  }

  @Override
  public void readJson(DbStrImpl fieldImpl, JsonReader reader) throws IOException {
    _strJsonReader.read(fieldImpl.getDbStr(), reader);
  }

  @Autowired
  private StrImplInitializer _strImplInitializer;

  @Autowired
  private StrModificationApplier _modificationApplier;

  @Autowired
  private StrJsonReader _strJsonReader;
}
