export class CourseWithTrainer{
  constructor (
    public name : string,
    public start_date : any,
    public end_date : any,
    public info : string,
    public skill_level : string,
    public trainer_id : number,
    public qty_per_week : number,
    public fname: string,
    public lname: string,
    public username: string,
    public email: string
   ){}
}
