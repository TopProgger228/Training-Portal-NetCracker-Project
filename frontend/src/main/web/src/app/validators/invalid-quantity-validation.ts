import {FormGroup, Validator} from '@angular/forms';

export class InvalidQuantityValidation{
  static validate(formGroup: FormGroup) {
    let qty = formGroup.controls.qty_per_week.value;

    if (qty < 1 || qty >4) {
      return { invalidQty : true };
    }

    return null;

  }
}
