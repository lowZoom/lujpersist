package luj.persist.anno.proc;

import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@Deprecated
public class DatabaseProcImpl {

  public void process(TypeElement elem, Elements elemUtil,
      Filer filer, Messager msg) throws IOException {
    //msg.printMessage(Kind.MANDATORY_WARNING, "中文：" + filer.getClass().getName());
    //
    //if () {
    //  msg.printMessage(Diagnostic.Kind.ERROR, , elem);
    //  return;
    //}

    ////if (!checkDbItem(item, msg)) {
    ////  return;
    ////}

  }

  //private DatabaseItem createDbItem(TypeElement elem, ) {
  //  List<SrcField> fieldList = elem
  //
  //      .collect(Collectors.toList());
  //
  //  return new DatabaseItem(, , fieldList);
  //}
  //
  ////private boolean checkDbItem(DatabaseItem item, Messager msg) {
  ////  List<FieldItem> invalidList = item.getFieldList().stream()
  ////      .filter(f -> FIELD_MAP.get(f.getSpec().type) == null)
  ////      .collect(Collectors.toList());
  ////
  ////  invalidList.forEach(f -> {
  ////    TypeName type = f.getSpec().type;
  ////    msg.printMessage(Diagnostic.Kind.ERROR, "无法识别的字段类型：" + type, f.getElem());
  ////  });
  ////
  ////  return invalidList.isEmpty();
  ////}
  //
  //private SrcField toDbField(ExecutableElement elem) {
  //  return new SrcField(elem, );
  //}
  //
  ////
  ////private static final Map<TypeName, FieldType> FIELD_MAP = ImmutableMap.<TypeName, FieldType>builder()
  ////    .put(TypeName.get(JStr.class), FieldType.of(String.class, "newStr"))
  ////    .put(TypeName.get(JTime.class), FieldType.of(long.class, "newTime"))
  ////    .build();
}
