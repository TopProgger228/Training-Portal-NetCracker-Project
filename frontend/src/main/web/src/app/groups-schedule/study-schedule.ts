export class StudySchedule {
  constructor(
    public course_id: number,
    public user_id: number,
    public time_slot_id: number,
    public is_choosen: boolean) {
  }
}
