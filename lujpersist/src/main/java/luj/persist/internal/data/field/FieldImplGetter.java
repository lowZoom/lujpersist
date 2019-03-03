package luj.persist.internal.data.field;

import luj.data.type.impl.Data;
import luj.persist.internal.data.impl.DataImplGetter;
import luj.persist.internal.data.meta.property.BeanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldImplGetter {

  public DataField get(BeanProperty property, Object rootData) {
    Data fieldData = property.getGetter().apply(rootData);
    return _dataImplGetter.get(fieldData);
  }

  @Autowired
  private DataImplGetter _dataImplGetter;
}
