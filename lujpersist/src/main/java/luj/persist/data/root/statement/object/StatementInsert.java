package luj.persist.data.root.statement.object;

import java.sql.SQLException;
import luj.persist.database.save.TransSaveStatParam;
import luj.persist.database.save.TransSaveStatement;

class StatementInsert implements TransSaveStatement {

  public StatementInsert(String sql, Long dataId) {
    _sql = sql;
    _dataId = dataId;
  }

  @Override
  public String getSql() {
    return _sql;
  }

  @Override
  public void applyParam(TransSaveStatParam param) throws SQLException {
    param.setLong(1, _dataId);
  }

  private final String _sql;

  private final Long _dataId;
}
