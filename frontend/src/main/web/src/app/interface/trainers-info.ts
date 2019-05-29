export class TrainersInfo {
    constructor(
      public id: number,
      public fname : string,
      public lname : string,
      public info : string,
      public courses : Array<string>,
      public photo : any
    ){

    }
}
