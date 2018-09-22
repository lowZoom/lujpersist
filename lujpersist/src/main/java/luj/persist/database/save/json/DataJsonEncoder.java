package luj.persist.database.save.json;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import luj.persist.database.json.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataJsonEncoder {

  public String encode(List<DJsEncodeField> fieldList) {
    try (StringWriter writer = new StringWriter()) {
      encodeImpl(writer, fieldList);
      return writer.toString();

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void encodeImpl(StringWriter writer, List<DJsEncodeField> fieldList) throws IOException {
    JsonGenerator generator = _jacksonFactory.createGenerator(writer);
    generator.writeStartObject();

    JsonWriter jsonWriter = new JsonWriterJackson(generator);
    for (DJsEncodeField property : fieldList) {
      property.writeToJson(jsonWriter);
    }

    generator.writeEndObject();
    generator.close();
  }

  @Autowired
  private JacksonFactory _jacksonFactory;
}
