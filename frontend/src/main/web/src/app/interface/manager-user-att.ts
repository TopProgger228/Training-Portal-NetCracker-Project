export interface ManagerUserAtt {

  id: number,
  userName: string,
  firstName: string,
  lastName: string,
  totalLessonCount: number,
  lessonsMap: { [reasonList: string]: number}

}
