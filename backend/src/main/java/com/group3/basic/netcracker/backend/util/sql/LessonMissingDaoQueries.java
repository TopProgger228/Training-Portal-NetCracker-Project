package com.group3.basic.netcracker.backend.util.sql;

public interface LessonMissingDaoQueries {

    String getLessonMissingByCourse = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm " +
            "join \"Lesson\" l on lm.lesson_id = l.id " +
            "join \"Course\" c on l.course_id = c.id " +
            "where c.id = ?";

    String getLessonMissingByLesson = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm " +
            "join \"Lesson\" l on lm.lesson_id = l.id " +
            "where l.id = ?";

    String getLessonMissingByUser = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm where lm.user_id = ?";

    String getLessonMissingByTrainer = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm " +
            "join \"Lesson\" l on lm.lesson_id = l.id " +
            "join \"Course\" c on l.course_id = c.id " +
            "where c.user_id = ?";

    String addLessonMissing = "insert into \"LessonMissing\" (user_id, lesson_id, reason) values (?, ?, ?)";

    String deleteLessonMissing = "delete from \"LessonMissing\" where user_id = ? and lesson_id = ?";

    String updateReason = "update \"LessonMissing\" set reason = ? where user_id = ? and lesson_id = ?";

    String getMissingLessonCountByUserAndCourse = "select count(lm.id) from \"LessonMissing\" lm left " +
            "join \"Lesson\" l on lm.lesson_id = L.id " +
            "left join \"Course\" c on l.course_id = c.id " +
            "where c.id =? and lm.user_id = ?";

    String getLessonMissingByLessonIdAndUserId = "select lm.id, lm.user_id, lm.lesson_id, lm.reason from \"LessonMissing\" lm " +
            "where lm.lesson_id = ? and lm.user_id = ?";

}
