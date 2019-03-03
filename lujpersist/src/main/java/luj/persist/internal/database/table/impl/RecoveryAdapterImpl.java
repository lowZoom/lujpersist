package luj.persist.internal.database.table.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.sql.DataSource;
import luj.persist.internal.data.meta.DbMeta;
import luj.persist.internal.data.meta.DbMetaMap;
import luj.persist.internal.database.jdbc.JdbcQueryRunner;
import luj.persist.internal.database.jdbc.JdbcUpdateRunner;
import luj.persist.internal.database.table.TabRecoverTable;
import luj.persist.internal.database.table.TableRecovery;
import luj.persist.internal.database.table.TableRecoveryAdapter;
import luj.persist.internal.database.table.scan.TableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RecoveryAdapterImpl implements TableRecoveryAdapter {

  @Override
  public void recover(DataSource dataSource) {
    DatabaseImpl database = new DatabaseImpl(dataSource, _jdbcUpdateRunner, _jdbcQueryRunner);

    List<TabRecoverTable> tableList = _dbMetaMap.values().stream()
        .flatMap(m -> toTable(m, database))
        .collect(Collectors.toList());

    _dbTableRecovery.recover(database, tableList);
  }

  private Stream<? extends TabRecoverTable> toTable(DbMeta meta, DatabaseImpl database) {
    TableData dataTable = new TableData(meta, database, _tableValidator);
    TableSet setTable = toSetTable(meta, database);

    return Stream.of(dataTable, setTable)
        .filter(Objects::nonNull);
  }

  private TableSet toSetTable(DbMeta meta, DatabaseImpl database) {
    String tableName = meta.getSetTableName();
    if (tableName == null) {
      return null;
    }
    return new TableSet(meta, database, _tableValidator);
  }

  @Autowired
  private TableRecovery _dbTableRecovery;

  @Autowired
  private JdbcUpdateRunner _jdbcUpdateRunner;

  @Autowired
  private JdbcQueryRunner _jdbcQueryRunner;

  @Autowired
  private DbMetaMap _dbMetaMap;

  @Autowired
  private TableValidator _tableValidator;
}
