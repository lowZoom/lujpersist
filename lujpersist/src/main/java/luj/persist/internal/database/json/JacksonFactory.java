package luj.persist.internal.database.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.io.Writer;
import org.springframework.stereotype.Service;

@Service
public class JacksonFactory {

  public JsonGenerator createGenerator(Writer w) throws IOException {
    return JSON_FACTORY.createGenerator(w);
  }

  public JsonParser createParser(String content) throws IOException {
    return JSON_FACTORY.createParser(content);
  }

  private static final JsonFactory JSON_FACTORY = new JsonFactory();
}
