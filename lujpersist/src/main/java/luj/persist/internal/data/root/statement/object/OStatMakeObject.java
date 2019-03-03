package luj.persist.internal.data.root.statement.object;

public interface OStatMakeObject {

  String getTableName();

  String getIdColumn();

  String getValColumn();

  Long getDataId();

  boolean isInDatabase();

  String toJson();
}
