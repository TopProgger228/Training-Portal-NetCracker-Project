package com.group3.basic.netcracker.backend.util.sql;

public interface Queries {
    String selectAllStudentsOfTrainer = "SELECT u.username, u.fname, u.lname" +
            "u.email AS \"Student\", t.trainer AS Trainer FROM \"User\" u" +
            "JOIN \"Group\" g ON g.user_id = u.id" +
            "JOIN \"Course\" c ON c.id = g.course_id" +
            "LEFT JOIN (SELECT c.id AS course, u.username AS trainer from \"User\" u JOIN \"Course\" c" +
            "ON c.user_id = u.id) AS t on t.course = course_id WHERE t.trainer LIKE 'yaTrainer'";
}
