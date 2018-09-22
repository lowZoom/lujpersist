package luj.persist.anno.proc.bean.implementation;

import java.io.IOException;
import java.util.List;

final class DbBeanImplGeneratorImpl implements DbBeanImplGenerator {

  interface BeanType {

    BeanImplementation createImplementation(String implName);
  }

  interface BeanImplementation {

    List<BeanField> getFieldList();

    void writeToFile() throws IOException;
  }

  interface BeanField {

    void addToImplementation();
  }

  DbBeanImplGeneratorImpl(BeanType beanType) {
    _beanType = beanType;
  }

  /**
   * @see luj.persist.anno.proc.bean.DbBeanImplGenerator#generate
   */
  @Override
  public void generate(String implName) throws IOException {
    BeanImplementation impl = _beanType.createImplementation(implName);

    for (BeanField field : impl.getFieldList()) {
      field.addToImplementation();
    }

    impl.writeToFile();
  }

  private final BeanType _beanType;
}
