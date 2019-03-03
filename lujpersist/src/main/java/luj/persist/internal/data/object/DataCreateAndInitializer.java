package luj.persist.internal.data.object;

import luj.data.type.impl.Data;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.meta.property.BeanProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCreateAndInitializer {

  public Object createAndInit(DbMeta dbMeta, Long dataId) {
    DataFactory<?> dataFactory = dbMeta.getDataFactory();
    Object data = _dataCreator.create(dataFactory, dataId, dbMeta);

    for (BeanProperty property : dbMeta.getPropertyList()) {
      initField(property, data);
    }

    return data;
  }

  private void initField(BeanProperty property, Object rootObj) {
    Data fieldData = property.getGetter().apply(rootObj);
    property.getFieldOp().initField(fieldData);
  }

  @Autowired
  private DataCreator _dataCreator;
}
