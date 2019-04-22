package com.group3.basic.netcracker.backend.grouptable.rowmapper;

import com.group3.basic.netcracker.backend.grouptable.entity.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setName(resultSet.getString("name"));
        group.setCourse_id(resultSet.getInt("course_id"));
        return group;
    }
}
