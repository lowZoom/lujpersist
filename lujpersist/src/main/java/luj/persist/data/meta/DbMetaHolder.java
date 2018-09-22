package luj.persist.data.meta;

import javax.annotation.PostConstruct;
import luj.ava.reflect.generic.GenericType;
import luj.persist.data.object.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DbMetaHolder<T> {

  public DbMeta getMeta() {
    return _meta;
  }

  @PostConstruct
  void init() {
    Class<T> dataType = getDataType(getClass());
    _meta = _dbMetaMaker.make(dataType, _dataFactory);
  }

  private Class<T> getDataType(Class<?> metaType) {
    return GenericType.fromSubclass(metaType).getTypeArg(0);
  }

  private DbMeta _meta;

  @Autowired
  private DbMetaMaker _dbMetaMaker;

  @Autowired
  private DataFactory<T> _dataFactory;
}
