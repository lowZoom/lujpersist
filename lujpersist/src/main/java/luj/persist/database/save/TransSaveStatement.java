package luj.persist.database.save;

import java.sql.SQLException;

public interface TransSaveStatement {

  String getSql();

  void applyParam(TransSaveStatParam param) throws SQLException;
}
