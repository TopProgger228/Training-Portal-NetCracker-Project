package com.group3.basic.netcracker.backend.util.sql;

public interface TokenDaoQueries {
    String createTokenQuery = "INSERT INTO \"UsersToken\" (email, token, expiry_date) VALUES (?,?,?)";

    String isTokenExistsQuery = "SELECT * FROM \"UsersToken\" WHERE token = ?;";

    String replaceTokenById = "UPDATE \"UsersToken\" SET token = ?, expiry_date = ? WHERE id = ?";

    String getEmailByTokenQuery = "SELECT email FROM \"UsersToken\" WHERE token = ?;";

    String isTokenAliveQuery = "SELECT expiry_date FROM \"UsersToken\" WHERE token = ?;";

    String getIdByEmailQuery = "SELECT * FROM \"UsersToken\" WHERE email = ?;";
}
