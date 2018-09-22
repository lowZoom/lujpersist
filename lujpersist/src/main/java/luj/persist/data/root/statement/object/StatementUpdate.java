package luj.persist.data.root.statement.object;

import java.sql.SQLException;
import luj.persist.database.save.TransSaveStatParam;
import luj.persist.database.save.TransSaveStatement;

class StatementUpdate implements TransSaveStatement {

  public StatementUpdate(String sql, String json, Long dataId) {
    _sql = sql;

    _json = json;
    _dataId = dataId;
  }

  @Override
  public String getSql() {
    return _sql;
  }

  @Override
  public void applyParam(TransSaveStatParam param) throws SQLException {
    param.setString(1, _json);
    param.setLong(2, _dataId);
  }

  private final String _sql;

  private final String _json;
  private final Long _dataId;
}
