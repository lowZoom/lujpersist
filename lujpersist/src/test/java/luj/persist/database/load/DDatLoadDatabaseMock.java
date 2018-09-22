package luj.persist.database.load;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import java.util.List;

class DDatLoadDatabaseMock implements DDatLoadDatabase, DDatLoadSet {

  public DDatLoadDatabaseMock() {
    _set = mock(DDatLoadSet.class);
  }

  @Override
  public List<QueryResult> runQuery(String sql) {
    QueryResult result = mock(QueryResult.class);
    when(result.getJsonStr()).thenReturn(sql);
    return ImmutableList.of(result);
  }

  @Override
  public String getDataTableName() {
    return _set.getDataTableName();
  }

  @Override
  public String getDataIdColumn() {
    return _set.getDataIdColumn();
  }

  @Override
  public String getDataValueColumn() {
    return _set.getDataValueColumn();
  }

  @Override
  public Iterable<Long> iter() {
    return _set.iter();
  }

  @Override
  public DecodeResult decodeData(Long dataId, String jsonStr) {
    return new DDatLoadSetDecodeResultMock(jsonStr);
  }

  private final DDatLoadSet _set;
}
