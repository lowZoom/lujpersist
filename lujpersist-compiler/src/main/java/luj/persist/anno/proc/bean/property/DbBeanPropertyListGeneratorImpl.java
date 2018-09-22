package luj.persist.anno.proc.bean.property;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import luj.persist.data.object.property.DataPropertyList;

final class DbBeanPropertyListGeneratorImpl implements DbBeanPropertyListGenerator {

  interface BeanType {

    TypeName toTypeName();

    List<BeanField> getFieldList();

    void savePropertyList(TypeSpec listClass) throws IOException;
  }

  interface BeanField {

    String getName();

    TypeName getType();
  }

  DbBeanPropertyListGeneratorImpl(BeanType dbType) {
    _dbType = dbType;
  }

  @Override
  public void generate(String listName) throws IOException {
    TypeSpec listClass = TypeSpec.classBuilder(listName)
        .addAnnotation(buildListAnno())
        .addModifiers(Modifier.FINAL)
        .addMethods(buildPropertyList())
        .build();

    _dbType.savePropertyList(listClass);
  }

  private AnnotationSpec buildListAnno() {
    return AnnotationSpec.builder(DataPropertyList.class)
        .addMember("value", "$T.class", _dbType.toTypeName())
        .build();
  }

  private List<MethodSpec> buildPropertyList() {
    return _dbType.getFieldList().stream()
        .map(this::buildProperty)
        .collect(Collectors.toList());
  }

  private MethodSpec buildProperty(BeanField field) {
    String fieldName = field.getName();
    TypeName dbType = _dbType.toTypeName();

    return MethodSpec.methodBuilder(fieldName)
        .returns(getPropertyFunc(dbType, field.getType()))
        .addStatement("return $T::$L", dbType, fieldName)
        .build();
  }

  private TypeName getPropertyFunc(TypeName dbType, TypeName propType) {
    return ParameterizedTypeName.get(ClassName.get(Function.class), dbType, propType);
  }

  private final BeanType _dbType;
}
