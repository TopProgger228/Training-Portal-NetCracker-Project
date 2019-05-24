import { FormGroup } from '@angular/forms';

export class UpperCaseValidator {
    static validate(registerFormGroup: FormGroup) {
        let fname = registerFormGroup.controls.fname.value;
        let lname = registerFormGroup.controls.lname.value;

        if (!((fname.charAt(0).toUpperCase() + fname.slice(1)) === fname)) {
            return {
                fnameIsLowerCase: true
            };
        };
        
        if (!((lname.charAt(0).toUpperCase() + lname.slice(1)) === lname)) {
            return {
                lnameIsLowerCase: true
            };
        }

        return null;

    }

}