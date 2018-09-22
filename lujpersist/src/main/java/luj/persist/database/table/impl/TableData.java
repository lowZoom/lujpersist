package luj.persist.database.table.impl;

import luj.persist.data.meta.DbMeta;
import luj.persist.data.object.database.ObjectColumn;
import luj.persist.database.table.TabRecoverTable;
import luj.persist.database.table.scan.TableValidateTable;
import luj.persist.database.table.scan.TableValidator;

class TableData implements TabRecoverTable, TableValidateTable {

  public TableData(DbMeta dbMeta, DatabaseImpl database, TableValidator tableValidator) {
    _dbMeta = dbMeta;

    _database = database;
    _tableValidator = tableValidator;
  }

  @Override
  public String getTableName() {
    return _dbMeta.getDataTableName();
  }

  @Override
  public boolean validate() {
    return _tableValidator.validate(_database, this);
  }

  @Override
  public String getColumnDef() {
    return ObjectColumn.ID.getName() + " BIGINT PRIMARY KEY,"
        + ObjectColumn.VALUE.getName() + " JSON NOT NULL";
  }

  @Override
  public String getSelectColumns() {
    return "id,val";
  }

  private final DbMeta _dbMeta;

  private final DatabaseImpl _database;
  private final TableValidator _tableValidator;
}
