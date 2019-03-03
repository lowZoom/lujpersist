package luj.persist.internal.database.table.scan;

import luj.persist.internal.database.table.TabRecoverTable;

public class ScanResult {

  /**
   * 表不存在
   */
  public static final String NOT_EXIST = "不存在";

  /**
   * 必要的列不存在
   */
  public static final String NO_COLUMN = "缺列";

  /**
   * 表里没数据
   */
  public static final String EMPTY = "没数据";

  public ScanResult(boolean hasError, TabRecoverTable table) {
    _hasError = hasError;
    _table = table;
  }

  public boolean hasError() {
    return _hasError;
  }

  public TabRecoverTable getTable() {
    return _table;
  }

  //private final DbMeta _meta;
  //private final String _tableName;
  //
  //private final TableTypeOp _tableTypeOp;
  //
  //private final Set<String> _exceptionSet;

  private final boolean _hasError;

  private final TabRecoverTable _table;
}
