package com.group3.basic.netcracker.backend.util.sql;

public interface UserDaoQueries {
    String addMemberQuery = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at) VALUES" +
            "(?, ?, ?, ?, ?, ?, ?)";

    String createUserQuery = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at, photo, manager_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    String resetPasswordQuery = "UPDATE \"User\" SET  pass = ? WHERE email = ?";

    String getUserByIdQuery = "SELECT * FROM \"User\" WHERE id = ?";

    String getIdByUsernameQuery = "SELECT id FROM \"User\" WHERE username = ?";

    String findByUsernameQuery = "SELECT * FROM \"User\" WHERE username = ?";

    String existsByUsernameQuery = "SELECT count(*) FROM \"User\" WHERE username = ?";

    String existsByEmailQuery = "SELECT count(*) FROM \"User\" WHERE email = ?";

    String usersListQuery = "SELECT * FROM \"User\"";

    String getTrainersQuery = "SELECT id, fname, lname, username, email FROM \"User\" where role= 'Trainer' ";

    String removeUserQuery = "DELETE FROM \"User\" WHERE id = ?";

    String updateUserQuery = "UPDATE \"User\" SET username = ?, fname = ?, lname = ?, email = ? WHERE id = ?";

    String updatePhotoQuery = "UPDATE \"User\" SET photo = ? WHERE username = ?";

    String getPhotoByUsername = "SELECT photo FROM \"User\" WHERE username = ?";

    String updateName = "UPDATE \"User\" SET fname = ?, lname = ? WHERE id = ?";

    String updateTrainersInfo = "UPDATE \"trainersinfo\" SET info = ? WHERE trainer_id = ?";

    String updateUserName = "UPDATE \"User\" SET username = ? WHERE id = ?";

    String updateUserFirstNameQuery = "UPDATE \"User\" SET fname = ? WHERE id = ?";

    String updateUserLastNameQuery = "UPDATE \"User\" SET lname = ? WHERE id = ?";

    String updateUserEmailQuery = "UPDATE \"User\" SET email = ? WHERE id = ?";

    String getUsersByLessonQuery = "select u.id, u.fname, u.lname, u.username, u.email, u.role, " +
            "u.created_at, u.manager_id, u.pass, u.photo from \"User\" u join \"Group\" " +
            "g on u.id = g.user_id join \"Course\" c on g.course_id = c.id join \"Lesson\" l " +
            "on c.id = l.course_id where l.id = ?";

    String getTrainerByCourseQuery = "select u.id, u.fname, u.lname, u.username, u.email, u.role, u.created_at, " +
            "u.manager_id, u.pass, u.photo from \"User\" u join \"Course\" c on u.id = c.trainer_id where c.id = ?";

    String getTrainersInfoQuery = "select id, fname, lname, photo, t.info from \"User\" u " +
            "join \"trainersinfo\" t on u.id = t.trainer_id";

    String insertTrainerInfoQuery = "INSERT INTO \"trainersinfo\" VALUES (?, ?)";

    String getIdQuery = "SELECT id FROM \"User\" WHERE username = ?";

    String isUserWithUsernameExistsQuery = "SELECT COUNT(*) FROM \"User\" WHERE username = ?";

    String isUserWithEmailExistsQuery = "SELECT COUNT(*) FROM \"User\" WHERE email = ?";

    String getUserByUsernameQuery = "SELECT fname, lname, username, email, id , photo FROM \"User\" WHERE username = ?";

    String getUsersListForDisplay = "SELECT fname, lname, username, email, id FROM \"User\" WHERE role = ?;";

    String getStudentsOfManagerQuery = "SELECT u.*, man.\"Manager\" \n" +
            "FROM \"User\" u\n" +
            "JOIN (SELECT id AS m_id, username AS \"Manager\" \n" +
            "\t\t   FROM \"User\") AS man ON man.m_id = u.manager_id\n" +
            "\t\t WHERE man.\"Manager\" = ?;";

    String getManagerOfStudent = "SELECT m.* FROM \"User\" AS m\n" +
            "WHERE m.id IN (\n" +
            "SELECT w.manager_id FROM\n" +
            "(SELECT u.manager_id AS manager_id, t.trainer AS \"Trainer\" FROM \"User\" u \n" +
            "                JOIN \"Group\" g ON g.user_id = u.id JOIN \"Course\" c ON c.id = g.course_id \n" +
            "                JOIN (SELECT c.id AS course, u.username AS trainer FROM \"User\" u JOIN \"Course\" c " +
            "ON c.trainer_id = u.id) AS t ON t.course = course_id \n WHERE t.trainer = ?) AS w);";

    String getStudentsOfTrainer = "select distinct u.username, u.fname, u.lname, u.email, \n" +
            "man.fname as \"ManagerFname\", man.lname as \"ManagerLname\",man.email as \"ManagerMail\", " +
            "man.username as \"ManagerUsername\"\n" +
            "from \"User\" u\n" +
            "join \"Group\" g on g.user_id = u.id\n" +
            "join \"Course\" c on c.id = g.course_id\n" +
            "left join (select c.id as course_id, u.username as trainer\n" +
            "from \"User\" u \n" +
            "join \"Course\" c on c.trainer_id = u.id) as t on t.course_id = g.course_id\n" +
            "left join (select id, username, fname, lname, email from \"User\" ) as man on man.id = u.manager_id\n" +
            "where t.trainer = ? order by u.username";

    String getTrainerCourses = "select name from \"Course\" where trainer_id = ?";

    String getStudentsByCourseName = "select fname, lname from \"User\" u join \"Group\" g\n" +
            "on u.id = g.user_id join \"Course\" c on g.course_id = c.id where c.name = ?";

    String getFreeManager = "select mng.id from \"User\" mng " +
            "left join \"User\" usr on mng.id = usr.manager_id " +
            "where mng.role = 'Manager' " +
            "group by mng.id " +
            "order by count(usr.id) " +
            "limit 1;";
}
