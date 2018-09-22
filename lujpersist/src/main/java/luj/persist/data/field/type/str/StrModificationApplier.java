package luj.persist.data.field.type.str;

import org.springframework.stereotype.Service;

@Service
public class StrModificationApplier {

  public void apply(DbStr state) {
    String newValue = state.getMod();
    state.setMod(null);

    state.setValue(newValue);
  }
}
