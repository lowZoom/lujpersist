package luj.persist.internal.data.root.statement.set.impl;

import java.util.List;
import luj.persist.internal.data.root.statement.set.SetStatementMaker;
import luj.persist.internal.data.set.DbSet;
import luj.persist.internal.database.save.TransSaveStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class SetStatementMakerAdapterImpl implements SetStatementMaker.Adapter {

  @Override
  public List<TransSaveStatement> make(DbSet dbSet) {
    SStatMakeSetImpl set = new SStatMakeSetImpl(dbSet);
    return _setStatementMaker.make(set);
  }

  @Autowired
  private SetStatementMaker _setStatementMaker;
}
