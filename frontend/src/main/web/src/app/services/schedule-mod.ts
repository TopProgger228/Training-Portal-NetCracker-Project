export class ScheduleMod {
  private _courseId: number;
  private _courseName: string;
  countVoted: number;
  isScheduled: string;


  constructor(courseId: number) {
    this._courseId = courseId;
  }

  get courseId(): number {
    return this._courseId;
  }

  get courseName(): string {
    return this._courseName;
  }


}
