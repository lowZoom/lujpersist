package luj.persist.internal.database.table.scan;

import java.sql.SQLException;

public interface TableValidateDatabase {

  void runQuery(String sql) throws SQLException;
}
