package luj.persist.internal.database.save;

import java.sql.SQLException;

public interface TransSaveStatParam {

  void setString(int paramIndex, String x) throws SQLException;

  void setLong(int paramIndex, Long x) throws SQLException;
}
