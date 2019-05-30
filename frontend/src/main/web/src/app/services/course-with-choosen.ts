export class CourseWithChoosen {
  constructor(
    public name : string,
    public start_date : any,
    public end_date : any,
    public info : string,
    public skill_level : string,
    public trainer_id : number,
    public qty_per_week : number,
    public choosen: number
  ){}
}
