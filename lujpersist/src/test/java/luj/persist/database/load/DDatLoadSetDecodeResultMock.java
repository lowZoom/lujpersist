package luj.persist.database.load;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DDatLoadSetDecodeResultMock implements DDatLoadSet.DecodeResult {

  public DDatLoadSetDecodeResultMock(String sql) {
    _result = mock(DDatLoadSet.DecodeResult.class);
    _sql = sql;
  }

  @Override
  public void markInDatabase() {
    when(_result.getDataObject()).thenReturn(_sql);
  }

  @Override
  public <T> T getDataObject() {
    return _result.getDataObject();
  }

  private final DDatLoadSet.DecodeResult _result;

  private final String _sql;
}
