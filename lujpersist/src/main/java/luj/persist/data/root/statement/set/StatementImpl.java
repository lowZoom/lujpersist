package luj.persist.data.root.statement.set;

import static com.google.common.base.Preconditions.checkNotNull;

import java.sql.SQLException;
import luj.persist.database.save.TransSaveStatParam;
import luj.persist.database.save.TransSaveStatement;

class StatementImpl implements TransSaveStatement {

  public StatementImpl(String sql, String key, Long val) {
    _sql = sql;

    _key = checkNotNull(key, sql);
    _val = checkNotNull(val, sql);
  }

  @Override
  public String getSql() {
    return _sql;
  }

  @Override
  public void applyParam(TransSaveStatParam param) throws SQLException {
    param.setString(1, _key);
    param.setLong(2, _val);
  }

  private final String _sql;

  private final String _key;
  private final Long _val;
}
