package luj.persist.data.set;

import java.util.Set;
import luj.persist.data.meta.DbMeta;

public class DbSet {

  public DbSet(String key, Set<Long> value, DbMeta meta) {
    _key = key;
    _meta = meta;

    _value = value;
  }

  public String getKey() {
    return _key;
  }

  public Set<Long> getAdd() {
    return _add;
  }

  public void setAdd(Set<Long> add) {
    _add = add;
  }

  public Set<Long> getRemove() {
    return _remove;
  }

  public void setRemove(Set<Long> remove) {
    _remove = remove;
  }

  public DbMeta getMeta() {
    return _meta;
  }

  public Set<Long> getValue() {
    return _value;
  }

  private Set<Long> _add;
  private Set<Long> _remove;

  private final String _key;
  private final Set<Long> _value;

  private final DbMeta _meta;
}
