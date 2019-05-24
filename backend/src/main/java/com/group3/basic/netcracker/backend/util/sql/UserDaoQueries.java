package com.group3.basic.netcracker.backend.util.sql;

public interface UserDaoQueries {
    String addMemberQuery = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at) VALUES" +
            "(?, ?, ?, ?, ?, ?, ?)";

    String createUserQuery = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at, photo) " +
            "VALUES (?,?,?,?,?,?,?,?)";

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

}
