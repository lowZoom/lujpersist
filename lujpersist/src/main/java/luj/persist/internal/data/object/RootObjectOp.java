package luj.persist.internal.data.object;

import java.util.List;
import luj.ava.spring.Internal;
import luj.persist.internal.data.root.RootOp;
import luj.persist.internal.data.root.modification.object.impl.RootObjectApplyMod;
import luj.persist.internal.data.root.statement.object.impl.RootObjectToSql;
import luj.persist.internal.database.save.TransSaveStatement;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
class RootObjectOp implements RootOp<DbObjectImpl> {

  @Override
  public void applyModification(DbObjectImpl rootImpl, Object dataObj) {
    _rootObjectApplyMod.call(dataObj, rootImpl.getDbObject());
  }

  @Override
  public List<TransSaveStatement> toSql(DbObjectImpl rootImpl, Object dataObj) {
    return _rootObjectToSql.toSql(dataObj, rootImpl.getDbObject());
  }

  @Autowired
  private RootObjectApplyMod _rootObjectApplyMod;

  @Autowired
  private RootObjectToSql _rootObjectToSql;
}
