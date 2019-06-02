import {TrainerAtt} from "./trainer-att";

export interface Class {
  lessonId: number;
  courseName: string;
  lessonDate: string;
  trainer: TrainerAtt;
  cancel: boolean;
}
