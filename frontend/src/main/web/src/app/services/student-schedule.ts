export class StudentSchedule {
  constructor(public courseName : string,
              public startTime : string,
              public endTime : string,
              public weekDay : string,
              public courseId : number,
              public isScheduled: string
  ){

  }
}
