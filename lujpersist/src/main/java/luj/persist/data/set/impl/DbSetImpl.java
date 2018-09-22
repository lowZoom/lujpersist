package luj.persist.data.set.impl;

import luj.data.type.impl.Data;
import luj.persist.data.root.DataRoot;
import luj.persist.data.root.RootOp;
import luj.persist.data.set.DbSet;

/**
 * @see Data#_impl
 */
class DbSetImpl implements DataRoot {

  public DbSetImpl(DbSet dbSet, RootOp<DataRoot> rootOp) {
    _dbSet = dbSet;
    _rootOp = rootOp;
  }

  public DbSet getDbSet() {
    return _dbSet;
  }

  @Override
  public RootOp<DataRoot> getRootOp() {
    return _rootOp;
  }

  private final DbSet _dbSet;

  private final RootOp<DataRoot> _rootOp;
}
