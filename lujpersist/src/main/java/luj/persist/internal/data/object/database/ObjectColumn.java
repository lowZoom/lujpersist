package luj.persist.internal.data.object.database;

public enum ObjectColumn {

  ID("id"),

  VALUE("val");

  public String getName() {
    return _name;
  }

  ObjectColumn(String name) {
    _name = name;
  }

  private final String _name;
}
