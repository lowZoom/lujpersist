package luj.persist.api.anno.proc.bean;

import java.io.IOException;

final class DatabaseAllGeneratorImpl implements DatabaseAllGenerator {

  interface BeanType {

    boolean isInterface();

    void logError(String msg);

    String getClassName();

    Implementation generateImplementation(String className) throws IOException;

    void generatePropertyList(String listName) throws IOException;

    void generateMeta(String metaName) throws IOException;
  }

  interface Implementation {

    void generateFactory(String factoryName) throws IOException;
  }

  DatabaseAllGeneratorImpl(BeanType type) {
    _type = type;
  }

  @Override
  public void generate() throws IOException {
    if (!_type.isInterface()) {
      _type.logError("只允许声明为接口");
      return;
    }

    String beanClassName = _type.getClassName();
    Implementation impl = _type.generateImplementation(beanClassName + "Impl");
    impl.generateFactory(beanClassName + "Factory");

    _type.generatePropertyList(beanClassName + "Property");
    _type.generateMeta(beanClassName + "Meta");
  }

  private final BeanType _type;
}
