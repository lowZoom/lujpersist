package luj.persist.internal.data.meta;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import luj.persist.api.anno.Database;
import luj.persist.internal.data.meta.property.BeanProperty;
import luj.persist.internal.data.meta.property.PropertyListMaker;
import luj.persist.internal.data.object.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class DbMetaMaker {

  public DbMeta make(Class<?> dataType, DataFactory<?> dataFactory) {
    Database anno = getAnno(dataType);
    String dataTableName = getDataTableName(dataType);
    String setTableName = getSetTableName(anno, dataTableName);

    List<BeanProperty> propertyList = _propertyListMaker.make(dataType);
    return new DbMeta(dataType, anno, dataTableName, setTableName, dataFactory, propertyList);
  }

  private Database getAnno(Class<?> dataType) {
    Database anno = dataType.getAnnotation(Database.class);
    return checkNotNull(anno, dataType.getName());
  }

  private String getDataTableName(Class<?> dataType) {
    return dataType.getSimpleName();
  }

  private String getSetTableName(Database anno, String dataTableName) {
    if (!anno.needSet()) {
      return null;
    }
    return dataTableName + "Set";
  }

  @Autowired
  private PropertyListMaker _propertyListMaker;
}
