package luj.persist.example.core;

import luj.data.type.JSet;
import luj.data.type.JStr;
import luj.persist.data.field.type.str.StrModifier;
import luj.persist.data.object.DataCreator;
import luj.persist.data.set.SetAdder;
import luj.persist.data.set.SetEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataOp {

  public <T> T create(Class<T> dataType, Long id) {
    return _dataCreator.create(dataType, id);
  }

  public void set(JStr str, String val) {
    _strModifier.modify(str, val);
  }

  public void add(JSet set, Long id) {
    _setAdder.add(set, id);
  }

  public boolean isEmpty(JSet set) {
    return _setEmptyChecker.check(set);
  }

  @Autowired
  private DataCreator _dataCreator;

  @Autowired
  private StrModifier _strModifier;

  @Autowired
  private SetAdder _setAdder;

  @Autowired
  private SetEmptyChecker _setEmptyChecker;
}
