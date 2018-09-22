package luj.persist.database.load;

public interface DDatLoadSet {

  interface DecodeResult {

    void markInDatabase();

    <T> T getDataObject();
  }

  String getDataTableName();

  String getDataIdColumn();

  String getDataValueColumn();

  Iterable<Long> iter();

  DecodeResult decodeData(Long dataId, String jsonStr);
}
