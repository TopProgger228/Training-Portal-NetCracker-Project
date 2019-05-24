import { Injectable } from '@angular/core';

declare var toastr: any;

@Injectable({
  providedIn: 'root'
})
export class AddNewManagerTrainerToasterService {

  constructor() {
    this.setting();
  }

  UserCreated(title : string, message? : string){
    toastr.success(title, message).css("font-size", "16px");
  }

  Error(title: string, message?: string) {
    toastr.error(title, message).css("font-size", "16px");

  }

  setting(){
    toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": true,
      "progressBar": false,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": true,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    };
  }
}
