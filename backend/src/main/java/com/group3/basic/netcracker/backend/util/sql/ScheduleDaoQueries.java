package com.group3.basic.netcracker.backend.util.sql;

public interface ScheduleDaoQueries {

    String isGroupExist = "SELECT count(*) FROM \"Group\" WHERE course_id = ? and user_id = ?";

    String getScheduleByIdQuery = "SELECT * FROM \"Schedule\" WHERE id = ?";

    String connectUserAndCourse = "INSERT INTO \"Group\" (course_id, user_id) VALUES (?,?)";

    String getScheduleListQuery = "SELECT id, user_id, time_slot_id, is_choosen FROM \"Schedule\"";

    String listScheduleWithCourseAndTimeSlotAndUserQuery =
            "select c.id,c.name as \"courseName\", count(sc.id) as \"countVoted\",\n" +
                    "case when coalesce(cast(t.choose as varchar), 'Not set yet') = 'true'\n" +
                    "then 'Schedule has been set' else 'Not set yet' end as \"isScheduled\"\n" +
                    "from \"Course\" c\n" +
                    "left join \"TimeSlot\" ts on ts.course_id = c.id\n" +
                    "left join \"Schedule\" sc on sc.time_slot_id = ts.id\n" +
                    "left join \"User\" u on u.id = sc.user_id\n" +
                    "left join (select distinct c.name as course, sc.is_choosen as choose\n" +
                    "from \"Schedule\" sc\n" +
                    "join \"TimeSlot\" ts on ts.id = sc.time_slot_id\n" +
                    "join \"Course\" c on c.id = ts.course_id\n" +
                    "where is_choosen is true) as t on t.course = c.name\n" +
                    "group by c.id, c.name, t.choose\n" +
                    "order by c.name";

    String removeScheduleQuery = "DELETE FROM \"Schedule\" WHERE id = ?";

    String updateScheduleQuery = "UPDATE \"Schedule\" SET user_id = ?, time_slot_id = ?, is_choosen = ? WHERE id = ?";

    String createScheduleQuery = "INSERT INTO \"Schedule\" (user_id, time_slot_id, is_choosen) VALUES (?,?,?)";

    String isScheduleWithAllRowQuery = "SELECT COUNT(*) FROM \"Schedule\" WHERE (user_id = ? AND time_slot_id = ? " +
            "AND is_choosen = ?)";

    String getScheduleOfStudentQuery = "select C2.name, TS.start_time, TS.end_time, TS.week_day, s.id, s.is_choosen " +
            "from \"Schedule\" as s join \"User\" U on s.user_id = U.id\n" +
            "join \"TimeSlot\" TS on s.time_slot_id = TS.id\n" +
            "join \"Course\" C2 on TS.course_id = C2.id\n" +
            "where U.username = ?";
}
