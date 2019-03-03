package luj.persist.internal.data.root.statement.object.impl;

import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.data.field.FieldImplGetter;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.object.DbObject;
import luj.persist.internal.data.root.statement.object.ObjectStatementMaker;
import luj.persist.internal.database.save.TransSaveStatement;
import luj.persist.internal.database.save.json.DJsEncodeField;
import luj.persist.internal.database.save.json.DataJsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootObjectToSql {

  public List<TransSaveStatement> toSql(Object data, DbObject dbObject) {
    DbMeta dbMeta = dbObject.getMeta();

    List<DJsEncodeField> fieldList = dbMeta.getPropertyList().stream()
        .map(p -> new DJsEncodeFieldImpl(data, p, _fieldImplGetter))
        .collect(Collectors.toList());

    OStatMakeObjectImpl obj = new OStatMakeObjectImpl(dbObject, dbMeta,
        fieldList, _dataJsonEncoder);

    return _objectStatementMaker.make(obj);
  }

  @Autowired
  private ObjectStatementMaker _objectStatementMaker;

  @Autowired
  private DataJsonEncoder _dataJsonEncoder;

  @Autowired
  private FieldImplGetter _fieldImplGetter;
}
