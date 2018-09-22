package luj.persist.data.root.statement.set.impl;

import java.util.List;
import luj.persist.data.root.statement.set.SetStatementMaker;
import luj.persist.data.set.DbSet;
import luj.persist.database.save.TransSaveStatement;
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
