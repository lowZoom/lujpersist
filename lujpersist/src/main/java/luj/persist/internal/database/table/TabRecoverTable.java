package luj.persist.internal.database.table;

public interface TabRecoverTable {

  String getTableName();

  boolean validate();

  String getColumnDef();
}
