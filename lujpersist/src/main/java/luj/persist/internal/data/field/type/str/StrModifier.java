package luj.persist.internal.data.field.type.str;

import luj.data.type.JStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrModifier {

  public void modify(JStr str, String value) {
    DbStrImpl impl = _strImplGetOrCreator.getOrCreate(str);
    impl.getDbStr().setMod(value);
  }

  @Autowired
  private StrImplGetOrCreator _strImplGetOrCreator;
}
