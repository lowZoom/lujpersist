package luj.persist.internal.database.load.json;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;

class DJsDecodeObjectMock implements DJsDecodeObject {

  public DJsDecodeObjectMock() {
    _result = new DataJsonDecoderTest.Result();
  }

  @Override
  public List<DJsDecodeField> getFieldList() {
    return ImmutableList.of(
        new Field1(),
        new Field2()
    );
  }

  @Override
  public Object getData() {
    return _result;
  }

  class Field1 implements DJsDecodeField {

    @Override
    public String getFieldName() {
      return "k1";
    }

    @Override
    public void readJson(JsonReader reader) throws IOException {
      _result.k1 = reader.readInt();
    }
  }

  class Field2 implements DJsDecodeField {

    @Override
    public String getFieldName() {
      return "k2";
    }

    @Override
    public void readJson(JsonReader reader) throws IOException {
      _result.k2 = reader.readStr();
    }
  }

  private final DataJsonDecoderTest.Result _result;
}
