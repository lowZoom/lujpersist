package luj.persist.internal.data.meta.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.ReflectTool;
import luj.ava.spring.BeanMapCollector;
import luj.data.type.impl.Data;
import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyListMaker {

  public List<BeanProperty> make(Class<?> dataType) {
    Object listBean = _listBeanFinder.find(dataType);
    Map<Class<?>, FieldOp<DataField>> opMap = collectOpMap();

    return Arrays.stream(listBean.getClass().getDeclaredMethods())
        .map(MetaBuilder::new)
        .map(this::resolveName)
        .map(b -> resolveGetter(b, listBean))
        .map(b -> resolveFieldOp(b, opMap))
        .map(MetaBuilder::getResult)
        .collect(Collectors.toList());
  }

  private Map<Class<?>, FieldOp<DataField>> collectOpMap() {
    return _beanMapCollector.collect(FieldOp.class, FieldOp::getDataType);
  }

  private MetaBuilder resolveName(MetaBuilder builder) {
    MetaImpl result = builder.getResult();
    Method method = builder.getPropertyMethod();

    result.setName(method.getName());
    return builder;
  }

  @SuppressWarnings("unchecked")
  private MetaBuilder resolveGetter(MetaBuilder builder, Object listBean) {
    try {
      Method method = builder.getPropertyMethod();
      method.setAccessible(true);
      Object getter = method.invoke(listBean);

      MetaImpl result = builder.getResult();
      result.setGetter((Function<Object, Data>) getter);
      return builder;

    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private MetaBuilder resolveFieldOp(MetaBuilder builder, Map<Class<?>, FieldOp<DataField>> opMap) {
    Method method = builder.getPropertyMethod();
    ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();

    Class<?> dataType = _reflectTool.getTypeArgument(returnType, 1);
    FieldOp<DataField> fieldOp = opMap.get(dataType);

    MetaImpl result = builder.getResult();
    result.setFieldOp(fieldOp);
    return builder;
  }

  @Autowired
  private ListBeanFinder _listBeanFinder;

  @Autowired
  private BeanMapCollector _beanMapCollector;

  @Autowired
  private ReflectTool _reflectTool;
}
