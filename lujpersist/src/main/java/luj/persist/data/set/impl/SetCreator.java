package luj.persist.data.set.impl;

import java.util.Set;
import luj.data.type.JSet;
import luj.data.type.impl.Impl;
import luj.persist.data.meta.DbMeta;
import luj.persist.data.root.DataRoot;
import luj.persist.data.root.RootOp;
import luj.persist.data.set.DbSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetCreator {

  public JSet create(String key, Set<Long> value, DbMeta meta) {
    DbSet dbSet = new DbSet(key, value, meta);
    DbSetImpl setImpl = new DbSetImpl(dbSet, getRootOp());

    JSet set = new JSet();
    Impl.set(set, setImpl);

    return set;
  }

  @SuppressWarnings("unchecked")
  private <T extends DataRoot> RootOp<T> getRootOp() {
    return (RootOp<T>) _rootSetOp;
  }

  @Autowired
  private RootOpSet _rootSetOp;
}
