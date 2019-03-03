package luj.persist.internal.data.set.database;

public enum SetColumn {

  KEY("key"),

  VALUE("val");

  public String getName() {
    return _name;
  }

  SetColumn(String name) {
    _name = name;
  }

  private final String _name;
}
