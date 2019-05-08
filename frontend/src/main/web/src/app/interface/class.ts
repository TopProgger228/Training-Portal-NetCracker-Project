import {TrainerAtt} from "./trainer-att";
import {UserAtt} from "./user-att";

export interface Class {
  lessonId: number;
  courseName: string;
  lessonDate: string;
  trainer: TrainerAtt;
  cancel: boolean;
  userAttendanceDtoList: UserAtt[]
}
