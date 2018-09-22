package luj.persist.database.table;

import javax.sql.DataSource;

public interface TableRecoveryAdapter {

  void recover(DataSource dataSource);
}
