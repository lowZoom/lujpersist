package luj.persist.internal.data.root.modification.object;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ObjectModificationApplier {

  public void apply(List<OModApplyField> fieldList) {
    fieldList.stream()
        .filter(f -> f.isModified())
        .forEach(f -> f.applyModification());
  }
}
