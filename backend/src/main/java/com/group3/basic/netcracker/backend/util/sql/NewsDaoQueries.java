package com.group3.basic.netcracker.backend.util.sql;

public interface NewsDaoQueries {
    String getNewsList = "SELECT * FROM \"News\"";

    String getActiveNewsList = "SELECT * FROM \"News\" WHERE Is_active = TRUE ";

    String updateNewsQuery = "UPDATE \"News\" SET title = ?, create_date = ?, context = ?, Is_active = ? WHERE id = ?";

    String createNewsQuery = "INSERT INTO \"News\" ( title, create_date, context, Is_active) VALUES (?,?,?,?)";
}
