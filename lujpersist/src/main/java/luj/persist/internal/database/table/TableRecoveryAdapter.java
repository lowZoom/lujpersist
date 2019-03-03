package luj.persist.internal.database.table;

import javax.sql.DataSource;

public interface TableRecoveryAdapter {

  void recover(DataSource dataSource);
}
