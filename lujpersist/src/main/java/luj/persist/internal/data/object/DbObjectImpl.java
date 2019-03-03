package luj.persist.internal.data.object;

import luj.data.type.impl.Data;
import luj.persist.internal.data.root.DataRoot;
import luj.persist.internal.data.root.RootOp;

/**
 * @see Data#_impl
 */
class DbObjectImpl implements DataRoot {

  public DbObjectImpl(DbObject dbObject, RootOp<DataRoot> rootOp) {
    _dbObject = dbObject;
    _rootOp = rootOp;
  }

  public DbObject getDbObject() {
    return _dbObject;
  }

  @Override
  public RootOp<DataRoot> getRootOp() {
    return _rootOp;
  }

  private final DbObject _dbObject;

  private final RootOp<DataRoot> _rootOp;
}
