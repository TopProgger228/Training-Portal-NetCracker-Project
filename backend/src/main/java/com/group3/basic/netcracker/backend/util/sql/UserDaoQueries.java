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
}
