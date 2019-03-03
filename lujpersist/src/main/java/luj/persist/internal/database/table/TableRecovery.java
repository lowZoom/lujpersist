package luj.persist.internal.database.table;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.database.table.scan.DbTableScanner;
import luj.persist.internal.database.table.scan.ScanResult;
import luj.persist.internal.database.table.sql.SqlTableCreator;
import luj.persist.internal.database.table.sql.SqlTableDropper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据库表检测修复
 */
@Service
public class TableRecovery {

  public void recover(TabRecoverDatabase database, Collection<TabRecoverTable> tableList) {
    List<ScanResult> scanList = _dbTableScanner.scan(tableList);

    List<TabRecoverTable> wrongList = scanList.stream()
        .filter(ScanResult::hasError)
        .map(ScanResult::getTable)
        .collect(Collectors.toList());

    if (wrongList.isEmpty()) {
      return;
    }

    dropWrong(database, wrongList);
    createMissing(database, wrongList);
  }

  private void dropWrong(TabRecoverDatabase database, List<TabRecoverTable> wrongList) {
    LOG.info("删除有问题的数据库表...");
    for (TabRecoverTable table : wrongList) {
      _sqlTableDropper.drop(database, table);
    }
  }

  private void createMissing(TabRecoverDatabase database, List<TabRecoverTable> wrongList) {
    LOG.info("重建缺失的数据库表...");
    for (TabRecoverTable table : wrongList) {
      _sqlTableCreator.create(database, table);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(TableRecovery.class);

  @Autowired
  private DbTableScanner _dbTableScanner;

  @Autowired
  private SqlTableDropper _sqlTableDropper;

  @Autowired
  private SqlTableCreator _sqlTableCreator;
}
