package luj.persist.internal.data.root.modification.object.impl;

import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldImplGetter;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.meta.property.BeanProperty;
import luj.persist.internal.data.object.DbObject;
import luj.persist.internal.data.root.modification.object.OModApplyField;
import luj.persist.internal.data.root.modification.object.ObjectModificationApplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootObjectApplyMod {

  public void call(Object data, DbObject dbObject) {
    DbMeta dbMeta = dbObject.getMeta();

    List<OModApplyField> fieldList = dbMeta.getPropertyList().stream()
        .map(p -> toField(p, data))
        .collect(Collectors.toList());

    _objectModificationApplier.apply(fieldList);
  }

  private OModApplyField toField(BeanProperty property, Object data) {
    DataField fieldImpl = _fieldImplGetter.get(property, data);
    return new OModApplyFieldImpl(fieldImpl, fieldImpl.getFieldOp());
  }

  @Autowired
  private FieldImplGetter _fieldImplGetter;

  @Autowired
  private ObjectModificationApplier _objectModificationApplier;
}
