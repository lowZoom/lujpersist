package luj.persist.internal.database.table.scan;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import luj.persist.internal.database.table.TabRecoverTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DbTableScanner {

  public List<ScanResult> scan(Collection<TabRecoverTable> metaList) {
    LOG.info("检测数据库表...");

    return metaList.stream()
        .map(this::scanTable)
        .collect(Collectors.toList());
  }

  private ScanResult scanTable(TabRecoverTable table) {
    String tableName = table.getTableName();
    checkNotNull(tableName);

    boolean valid = table.validate();
    return new ScanResult(!valid, table);
  }

  private static final Logger LOG = LoggerFactory.getLogger(DbTableScanner.class);
}
