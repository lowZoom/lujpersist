package luj.persist.internal.data.meta;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import luj.ava.reflect.ReflectTool;
import luj.ava.spring.BeanMapCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DbMetaMap {

  public DbMeta get(Class<?> dataType) {
    return _metaMap.get(dataType);
  }

  public Collection<DbMeta> values() {
    return _metaMap.values();
  }

  @PostConstruct
  void init() {
    _metaMap = _beanMapCollector.collect(DbMetaHolder.class, this::getKey).entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getMeta()));
  }

  private Class<?> getKey(DbMetaHolder<?> metaBean) {
    ReflectTool t = _reflectTool;
    Class<?> beanType = metaBean.getClass();
    ParameterizedType metaType = t.getGenericSuperclass(beanType);
    return t.getTypeArgument(metaType, 0);
  }

  private Map<Class<?>, DbMeta> _metaMap;

  @Autowired
  private BeanMapCollector _beanMapCollector;

  @Autowired
  private ReflectTool _reflectTool;
}
