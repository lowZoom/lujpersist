package luj.persist.internal.data.meta;

import java.util.List;
import luj.persist.api.anno.Database;
import luj.persist.internal.data.meta.property.BeanProperty;
import luj.persist.internal.data.object.DataFactory;

public class DbMeta {

  public DbMeta(Class<?> dataType, Database dataAnno, String dataTableName, String setTableName,
      DataFactory<?> dataFactory, List<BeanProperty> propertyList) {
    _dataType = dataType;
    _dataAnno = dataAnno;

    _dataTableName = dataTableName;
    _setTableName = setTableName;

    _dataFactory = dataFactory;
    _propertyList = propertyList;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public Database getDataAnno() {
    return _dataAnno;
  }

  public String getDataTableName() {
    return _dataTableName;
  }

  public String getSetTableName() {
    return _setTableName;
  }

  public DataFactory<?> getDataFactory() {
    return _dataFactory;
  }

  public List<BeanProperty> getPropertyList() {
    return _propertyList;
  }

  private final Class<?> _dataType;
  private final Database _dataAnno;

  private final String _dataTableName;
  private final String _setTableName;

  private final DataFactory<?> _dataFactory;
  private final List<BeanProperty> _propertyList;
}
