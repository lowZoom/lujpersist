package luj.persist.database.table.scan;

import java.sql.SQLException;

public interface TableValidateDatabase {

  void runQuery(String sql) throws SQLException;
}
