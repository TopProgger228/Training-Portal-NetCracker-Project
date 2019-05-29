package com.group3.basic.netcracker.backend.util.sql;

public interface ReporterDaoQueries {
    String queryForReportByTrainer = "select count(lm.id) as \"MissingQty\", \n" +
            "       u.username as \"Student\", c.name as \"Course\",\n" +
            "\t   lm.reason as \"Reason\", t.username as \"Trainer\"\n" +
            "       from \"LessonMissing\" lm join \"User\" u on lm.user_id = u.id \n" +
            "       join \"Group\" g on u.id = g.user_id \n" +
            "       join \"Course\" c on c.id = g.course_id\n" +
            "       join (select id, username from \"User\") as t on t.id = c.trainer_id\n" +
            "              where t.username = ? \n" +
            "              group by u.username, lm.reason, c.name, t.username,t.id";

    String queryReportByStudent = "select count(lm.id) as \"MissingQty\", \n" +
            "coalesce(u.fname || ' ' || u.lname, '')\n" +
            "as \"Student\", crs.\"Course\", lm.reason as \"Reason\" \n" +
            "from \"LessonMissing\" lm \n" +
            "join \"User\" u on u.id = lm.user_id\n" +
            "join (select c.id, c.name as \"Course\", g.user_id\n" +
            "\t from \"Course\" c\n" +
            "\t join \"Group\" g on g.course_id = c.id) as crs on crs.user_id = u.id \n" +
            "where u.username = ?\n" +
            "group by u.fname, u.lname, crs.\"Course\", lm.reason\n";

    String queryReportByLevel = "select count(lm.id) as \"MissingQty\", \n" +
            "coalesce(u.fname || ' ' || u.lname, '')\n" +
            "as \"Student\", crs.\"Course\", lm.reason as \"Reason\" \n" +
            "from \"LessonMissing\" lm \n" +
            "join \"User\" u on u.id = lm.user_id\n" +
            "join (select c.id, c.name as \"Course\",\n" +
            "\t g.user_id, c.skill_level\n" +
            "\t from \"Course\" c\n" +
            "\t join \"Group\" g on g.course_id = c.id) as crs on crs.user_id = u.id \n" +
            "where crs.skill_level = ?\n" +
            "group by crs.\"Course\", u.fname, u.lname, lm.reason";
}
