package com.group3.basic.netcracker.backend.util.sql;

public interface LessonDaoQueries {

    String getLessonByCourse = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ? order by l.lesson_date";

    String getLessonById = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l where l.id = ?";

    String getTodayLessonsByTrainer = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.trainer_id = ? and l.lesson_date = cast(current_timestamp as date)";

    String getTodayLessonsByTrainerUsername = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id join \"User\" u on c.trainer_id = u.id where u.username = ? and l.lesson_date = cast(current_timestamp as date)";

    String getLessonCountTillTodayByStudent = "select count(l.id) from \"Lesson\" l " +
            "join \"Course\" c on l.course_id = c.id " +
            "join \"Group\" g on c.id = g.course_id " +
            "join \"User\" u on g.user_id = u.id " +
            "join \"TimeSlot\" ts on l.time_slot_id = ts.id " +
            "where u.id = ? and l.lesson_date <= cast(current_timestamp as date) " +
            "and (l.is_cancel = false or l.is_cancel is null)";

    String getLessonCountInCourseTillToday = "select count(l.id) from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ? and l.lesson_date <= cast(current_timestamp as date) and (l.is_cancel = false or l.is_cancel is null)";

    String getLessonCountInCourse = "select count(l.id) from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ?";

    String updateActiveStatus = "update \"Lesson\" set is_cancel=? where id=?";
}
