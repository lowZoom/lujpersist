package luj.persist.internal.data.root.modification.object.impl;

import luj.persist.internal.data.field.DataField;
import luj.persist.internal.data.field.FieldOp;
import luj.persist.internal.data.root.modification.object.OModApplyField;

class OModApplyFieldImpl implements OModApplyField {

  public OModApplyFieldImpl(DataField field, FieldOp<DataField> fieldOp) {
    _field = field;
    _fieldOp = fieldOp;
  }

  @Override
  public boolean isModified() {
    return _fieldOp.isDirty(_field);
  }

  @Override
  public void applyModification() {
    _fieldOp.applyModification(_field);
  }

  private final DataField _field;
  private final FieldOp<DataField> _fieldOp;
}
