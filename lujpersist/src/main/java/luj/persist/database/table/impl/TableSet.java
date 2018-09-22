package luj.persist.database.table.impl;

import luj.persist.data.meta.DbMeta;
import luj.persist.data.set.database.SetColumn;
import luj.persist.database.table.TabRecoverTable;
import luj.persist.database.table.scan.TableValidateTable;
import luj.persist.database.table.scan.TableValidator;

class TableSet implements TabRecoverTable, TableValidateTable {

  public TableSet(DbMeta dbMeta, DatabaseImpl database, TableValidator tableValidator) {
    _dbMeta = dbMeta;

    _database = database;
    _tableValidator = tableValidator;
  }

  @Override
  public String getTableName() {
    return _dbMeta.getSetTableName();
  }

  @Override
  public boolean validate() {
    return _tableValidator.validate(_database, this);
  }

  @Override
  public String getColumnDef() {
    return SetColumn.KEY.getName() + " VARCHAR(255) NOT NULL,"
        + SetColumn.VALUE.getName() + " BIGINT NOT NULL";
  }

  @Override
  public String getSelectColumns() {
    return SetColumn.KEY.getName() + ',' + SetColumn.VALUE.getName();
  }

  private final DbMeta _dbMeta;

  private final DatabaseImpl _database;
  private final TableValidator _tableValidator;
}
