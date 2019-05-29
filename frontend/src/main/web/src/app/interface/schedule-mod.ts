import {Input} from "@angular/core";

export class ScheduleMod {
  private _courseId: number;
  private _courseName: string;
  countVoted: number;
  private _isScheduled: string;


  constructor(courseId: number) {
    this._courseId = courseId;
  }

  get courseId(): number {
    return this._courseId;
  }

  get courseName(): string {
    return this._courseName;
  }


  get isScheduled(): string {
    return this._isScheduled;
  }

  set isScheduled(value: string) {
    this._isScheduled = value;
  }
}
