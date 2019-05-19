import { Injectable } from '@angular/core';
declare var toastr: any;
@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor() {

    this.setting();

  }

  Success(title: string, message?: string) {

    toastr.success(title, message).css(
      "font-size", "16px"
    )

  }

  Warning(title: string, message?: string) {

    toastr.warning(title, message).css(
      "font-size", "16px"
    )

  }

  Error(title: string, message?: string) {

    toastr.error(title, message).css(
      "font-size", "16px"
    )

  }

  Info(message: string) {

    toastr.info(message).css(
      "font-size", "16px"
    )

  }

  setting() {

    toastr.options = {
      "closeButton": false,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut",

    }

  }

}
