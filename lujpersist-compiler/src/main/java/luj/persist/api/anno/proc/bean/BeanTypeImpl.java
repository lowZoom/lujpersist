package luj.persist.api.anno.proc.bean;

import java.io.IOException;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.generate.annotation.process.type.ProcType;
import luj.persist.api.anno.proc.bean.factory.DbBeanFactoryGenerator;
import luj.persist.api.anno.proc.bean.implementation.DbBeanImplGenerator;
import luj.persist.api.anno.proc.bean.meta.DbMetaGenerator;
import luj.persist.api.anno.proc.bean.property.DbBeanPropertyListGenerator;

final class BeanTypeImpl implements DatabaseAllGeneratorImpl.BeanType {

  BeanTypeImpl(SingleAnnoProc.Context ctx) {
    _procType = ctx.getProcessingType();
    _typeElement = _procType.toElement();

    _ctx = ctx;

    _beanImplGeneratorFactory = DbBeanImplGenerator.Factory.get();
    _beanPropertyListGeneratorFactory = DbBeanPropertyListGenerator.Factory.get();
  }

  @Override
  public boolean isInterface() {
    return _typeElement.getKind() == ElementKind.INTERFACE;
  }

  @Override
  public void logError(String msg) {
    _procType.getLogger().error(msg);
  }

  @Override
  public String getClassName() {
    return _typeElement.getSimpleName().toString();
  }

  @Override
  public DatabaseAllGeneratorImpl.Implementation generateImplementation(
      String className) throws IOException {
    DbBeanImplGenerator g = _beanImplGeneratorFactory.instance(_ctx);
    g.generate(className);

    return new ImplementationImpl(className, _ctx, DbBeanFactoryGenerator.Factory.get());
  }

  @Override
  public void generatePropertyList(String listName) throws IOException {
    DbBeanPropertyListGenerator generator = _beanPropertyListGeneratorFactory.instance(_ctx);
    generator.generate(listName);
  }

  @Override
  public void generateMeta(String metaName) throws IOException {
    DbMetaGenerator generator = DbMetaGenerator.Factory.create(_ctx);
    generator.generate(metaName);
  }

  private final ProcType _procType;
  private final TypeElement _typeElement;

  private final SingleAnnoProc.Context _ctx;

  private final DbBeanImplGenerator.Factory _beanImplGeneratorFactory;
  private final DbBeanPropertyListGenerator.Factory _beanPropertyListGeneratorFactory;
}
