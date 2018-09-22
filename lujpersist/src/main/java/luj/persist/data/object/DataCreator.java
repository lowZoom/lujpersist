package luj.persist.data.object;

import static com.google.common.base.Preconditions.checkState;

import java.util.Objects;
import luj.data.type.impl.Data;
import luj.data.type.impl.Impl;
import luj.persist.data.meta.DbMeta;
import luj.persist.data.meta.DbMetaMap;
import luj.persist.data.root.DataRoot;
import luj.persist.data.root.RootOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCreator {

  @SuppressWarnings("unchecked")
  public <T> T create(Class<T> dataType, Long id) {
    DbMeta dbMeta = _dbMetaMap.get(dataType);

    Class<?> typeInMeta = dbMeta.getDataType();
    checkState(Objects.equals(typeInMeta, dataType),
        "%s <-> %s", typeInMeta.getName(), dataType.getName());

    DataFactory<T> dataFactory = (DataFactory<T>) dbMeta.getDataFactory();
    return create(dataFactory, id, dbMeta);
  }

  public <T> T create(DataFactory<T> dataFactory, Long id, DbMeta dbMeta) {
    T data = dataFactory.create();
    initImpl((Data) data, id, dbMeta, _rootObjectOp);
    return data;
  }

  @SuppressWarnings("unchecked")
  private void initImpl(Data dataObj, Long id, DbMeta meta, RootOp<?> rootOp) {
    DbObject dbObject = new DbObject(id, meta);
    DbObjectImpl impl = new DbObjectImpl(dbObject, (RootOp<DataRoot>) rootOp);

    Impl.set(dataObj, impl);
  }

  @Autowired
  private DbMetaMap _dbMetaMap;

  @Autowired
  private RootObjectOp _rootObjectOp;
}
