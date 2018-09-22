package luj.persist.database.table;

import java.util.ArrayList;
import java.util.List;

class DatabaseMock implements TabRecoverDatabase {

  public DatabaseMock() {
    _runHistory = new ArrayList<>();
  }

  public List<String> getRunHistory() {
    return _runHistory;
  }

  @Override
  public void runUpdate(String sql) {
    _runHistory.add(sql);
  }

  private final List<String> _runHistory;
}
