import { FormGroup } from '@angular/forms';
 
export class PasswordValidator {
    static validate(passwordResetFormGroup: FormGroup) {
        let password = passwordResetFormGroup.controls.password.value;
        let repeatPassword = passwordResetFormGroup.controls.repeatPassword.value;
 
        if (repeatPassword.length <= 0) {
            return null;
        }
 
        if (repeatPassword !== password) {
            return {
                doesMatchPassword: true
            };
        }
 
        return null;
 
    }
}