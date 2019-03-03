package luj.persist.internal.data.meta.property;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.data.object.property.DataPropertyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
class ListBeanFinder {

  public Object find(Class<?> dataType) {
    List<Object> resultList = _applicationContext
        .getBeansWithAnnotation(DataPropertyList.class).values().stream()
        .map(this::toPair)
        .filter(p -> p.anno.value() == dataType)
        .map(p -> p.bean)
        .collect(Collectors.toList());

    checkState(resultList.size() == 1, "%s -> %s", dataType, resultList);
    return resultList.get(0);
  }

  private BeanPair toPair(Object bean) {
    BeanPair pair = new BeanPair();
    pair.bean = bean;

    Class<?> beanType = bean.getClass();
    pair.anno = checkNotNull(beanType.getAnnotation(DataPropertyList.class), beanType.getName());

    return pair;
  }

  private static class BeanPair {

    Object bean;

    DataPropertyList anno;
  }

  @Autowired
  private ApplicationContext _applicationContext;
}
