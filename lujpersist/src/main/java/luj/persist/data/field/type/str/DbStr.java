package luj.persist.data.field.type.str;

public class DbStr {

  public String getValue() {
    return _value;
  }

  public void setValue(String value) {
    _value = value;
  }

  public String getMod() {
    return _mod;
  }

  public void setMod(String mod) {
    _mod = mod;
  }

  private String _value;

  private String _mod;
}
