package luj.persist.internal.database.table;

import static org.mockito.Mockito.mock;

class TableMock implements TabRecoverTable {

  public TableMock() {
    _tableMock = mock(TabRecoverTable.class);
  }

  @Override
  public String getTableName() {
    return _tableMock.getTableName();
  }

  @Override
  public boolean validate() {
    return _tableMock.validate();
  }

  @Override
  public String getColumnDef() {
    return _tableMock.getColumnDef();
  }

  private final TabRecoverTable _tableMock;
}
