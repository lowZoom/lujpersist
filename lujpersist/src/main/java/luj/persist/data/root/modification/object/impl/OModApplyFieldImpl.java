package luj.persist.data.root.modification.object.impl;

import luj.persist.data.field.DataField;
import luj.persist.data.field.FieldOp;
import luj.persist.data.root.modification.object.OModApplyField;

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
