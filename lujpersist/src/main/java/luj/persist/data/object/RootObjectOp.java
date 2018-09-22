package luj.persist.data.object;

import java.util.List;
import luj.persist.data.root.RootOp;
import luj.persist.data.root.modification.object.impl.RootObjectApplyMod;
import luj.persist.data.root.statement.object.impl.RootObjectToSql;
import luj.persist.database.save.TransSaveStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
