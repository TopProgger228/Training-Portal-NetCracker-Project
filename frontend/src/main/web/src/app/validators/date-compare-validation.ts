import { FormGroup } from '@angular/forms';

export class DateComparerValidation {
  static validate(dateFormGroup: FormGroup) {
    let start_date = dateFormGroup.controls.start_date.value;
    let end_date = dateFormGroup.controls.end_date.value;

    if (start_date > end_date) {
      return {
        compareDate: true
      };
    }

    return null;

  }
}
