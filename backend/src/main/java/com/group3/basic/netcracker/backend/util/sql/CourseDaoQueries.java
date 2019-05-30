package com.group3.basic.netcracker.backend.util.sql;

public interface CourseDaoQueries {
    String createCourseQuery = "INSERT INTO \"Course\" (name, start_date, end_date, info, skill_level, " +
            "trainer_id, qty_per_week) VALUES (?,?,?,?,?,?,?)";

    String getCourseById = "SELECT * FROM \"Course\" WHERE id = ?";

    String getIdByCourseNameQuery = "SELECT id FROM \"Course\" WHERE name = ?";

    String getCourseByName = "SELECT name, start_date, end_date, info, skill_level, " +
            "trainer_id, qty_per_week, id FROM \"Course\" WHERE name = ?";

    String getCoursesList = "SELECT id, name, info, trainer_id, skill_level, " +
            "start_date, end_date, qty_per_week FROM \"Course\"";

    String getActiveCoursesList = "SELECT distinct c.id, c.name, c.info, c.trainer_id, c.skill_level,\n" +
            "                c.start_date, c.end_date, c.qty_per_week,\n" +
            "                sum(case when S.is_choosen = true\n" +
            "                then 1\n" +
            "                else 0 end) as choosen\n" +
            "                FROM \"Course\" as c\n" +
            "                join \"TimeSlot\" TS on c.id = TS.course_id\n" +
            "                left join \"Schedule\" S on TS.id = S.time_slot_id\n" +
            "                WHERE start_date > ?\n" +
            "                group by c.id, c.name, c.info\n" +
            "                ORDER BY id DESC";

    String getListCoursesByUsername = "SELECT id, name, info, trainer_id, skill_level, start_date, end_date," +
            " qty_per_week FROM \"Course\" where trainer_id=(select id from \"User\" where username=?)";

    String removeCourse = "DELETE FROM \"Course\" WHERE id = ?";

    String updateCourse = "UPDATE \"Course\" SET name = ?, start_date = ?, end_date = ?, info = ?," +
            " skill_level = ?, trainer_id = ?, qty_per_week = ? WHERE id = ?";

    String getCourseByLessonQuery = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date," +
            " c.end_date, c.qty_per_week from \"Course\" c join \"Lesson\" l on c.id = l.course_id where  l.id = ?";

    String getCourseByUserUsernameQuery = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date," +
            " c.end_date, c.qty_per_week from \"Course\" c join \"Group\" g on c.id = g.course_id join \"User\" u " +
            "on g.user_id = u.id where u.username = ?";

    String getCourseByUserId = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date," +
            " c.end_date, c.qty_per_week from \"Course\" c join \"Group\" g " +
            "on c.id = g.course_id join \"User\" u on g.user_id = u.id where u.id = ?";

    String getCourseByTrainerUsername = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date," +
            " c.end_date, c.qty_per_week from \"Course\" c join \"User\" t on c.trainer_id = t.id where t.username = ?";

    String getCourseBySkillLevel = "select c.id, c.name, c.info, c.trainer_id," +
            " c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c where c.skill_level = ?";

    String getCoursesWithTrainerByUsername = "select c.name, c.info, c.skill_level, c.start_date, c.end_date," +
            " c.qty_per_week, u.username, u.fname, u.lname," +
            " u.email from (((\"Course\" c join \"Group\" g on c.id=g.course_id)" +
            " join \"User\" u on c.trainer_id=u.id) join \"User\" us on g.user_id=us.id) where us.username= ?";

}
