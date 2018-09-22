package luj.persist.database.table;

public interface TabRecoverTable {

  String getTableName();

  boolean validate();

  String getColumnDef();
}
