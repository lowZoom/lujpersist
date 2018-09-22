package luj.persist.database.load.json;

import java.util.List;

public interface DJsDecodeObject {

  List<DJsDecodeField> getFieldList();

  Object getData();
}
