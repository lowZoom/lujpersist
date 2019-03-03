package luj.persist.internal.database.save;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatementParamMock implements TransSaveStatParam {

  public StatementParamMock() {
    _paramMap = new HashMap<>();
  }

  public Object[] apply(TransSaveStatement statement) {
    try {
      statement.applyParam(this);

    } catch (SQLException e) {
      throw new AssertionError(e);
    }

    return _paramMap.values().toArray();
  }

  @Override
  public void setString(int paramIndex, String x) {
    _paramMap.put(paramIndex, x);
  }

  @Override
  public void setLong(int paramIndex, Long x) {
    _paramMap.put(paramIndex, x);
  }

  private final Map<Integer, Object> _paramMap;
}
