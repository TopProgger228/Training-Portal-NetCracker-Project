import {TrainerAtt} from "./trainer-att";

export interface CourseAtt {
  courseId: number,
  name: string,
  skillLevel: string,
  trainer: TrainerAtt
  totalLessonCount: number,
  presentLessonCount: number,
  finishedLessonCount: number
}
