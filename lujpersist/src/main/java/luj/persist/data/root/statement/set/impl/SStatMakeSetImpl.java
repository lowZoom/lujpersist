package luj.persist.data.root.statement.set.impl;

import static com.google.common.base.MoreObjects.firstNonNull;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import luj.persist.data.meta.DbMeta;
import luj.persist.data.root.statement.set.SStatMakeSet;
import luj.persist.data.set.DbSet;
import luj.persist.data.set.database.SetColumn;

class SStatMakeSetImpl implements SStatMakeSet {

  public SStatMakeSetImpl(DbSet dbSet) {
    _dbSet = dbSet;
    _dbMeta = dbSet.getMeta();
  }

  @Override
  public String getTableName() {
    return _dbMeta.getSetTableName();
  }

  @Override
  public String getKeyColumn() {
    return SetColumn.KEY.getName();
  }

  @Override
  public String getValColumn() {
    return SetColumn.VALUE.getName();
  }

  @Override
  public String getKey() {
    return _dbSet.getKey();
  }

  @Override
  public Set<Long> getAddHistory() {
    return firstNonNull(_dbSet.getAdd(), ImmutableSet.of());
  }

  @Override
  public Set<Long> getRemoveHistory() {
    return firstNonNull(_dbSet.getRemove(), ImmutableSet.of());
  }

  private final DbSet _dbSet;
  private final DbMeta _dbMeta;
}
