package luj.persist.data.set.impl;

import java.util.List;
import luj.persist.data.root.RootOp;
import luj.persist.data.root.statement.set.SetStatementMaker;
import luj.persist.database.save.TransSaveStatement;
import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RootOpSet implements RootOp<DbSetImpl> {

  @Override
  public void applyModification(DbSetImpl rootImpl, Object dataObj) {
    throw new NO_IMPLEMENT("applyModification尚未实现");
  }

  @Override
  public List<TransSaveStatement> toSql(DbSetImpl rootImpl, Object dataObj) {
    return _setStatementMaker.make(rootImpl.getDbSet());
  }

  @Autowired
  private SetStatementMaker.Adapter _setStatementMaker;
}
