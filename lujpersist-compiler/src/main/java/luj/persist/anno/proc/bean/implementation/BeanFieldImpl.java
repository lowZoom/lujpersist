package luj.persist.anno.proc.bean.implementation;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import org.springframework.util.StringUtils;

final class BeanFieldImpl implements DbBeanImplGeneratorImpl.BeanField {

  BeanFieldImpl(ExecutableElement fieldElement,
      TypeSpec.Builder implClass, MethodSpec.Builder implConstructor) {
    _fieldElement = fieldElement;

    _implClass = implClass;
    _implConstructor = implConstructor;
  }

  @Override
  public void addToImplementation() {
    TypeName fieldType = TypeName.get(_fieldElement.getReturnType());
    String fieldName = _fieldElement.getSimpleName().toString();

    FieldSpec implField = makeField(fieldType, fieldName);
    _implClass.addField(implField);
    _implClass.addMethod(makeProperty(implField));

    _implConstructor.addStatement("$L = new $T()", implField.name, fieldType);
  }

  private FieldSpec makeField(TypeName fieldType, String fieldName) {
    return FieldSpec.builder(fieldType, '_' + fieldName, Modifier.FINAL).build();
  }

  private MethodSpec makeProperty(FieldSpec implField) {
    String fieldName = implField.name;
    return MethodSpec.methodBuilder(nameOfProperty("", fieldName))
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .returns(implField.type)
        .addStatement("return $L", fieldName)
        .build();
  }

  private String nameOfProperty(String prefix, String fieldName) {
    String result = prefix + StringUtils.capitalize(fieldName.substring(1));
    return StringUtils.uncapitalize(result);
  }

  private final ExecutableElement _fieldElement;

  private final TypeSpec.Builder _implClass;
  private final MethodSpec.Builder _implConstructor;
}
