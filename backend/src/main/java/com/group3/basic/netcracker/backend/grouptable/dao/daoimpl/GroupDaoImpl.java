package com.group3.basic.netcracker.backend.grouptable.dao.daoimpl;

import com.group3.basic.netcracker.backend.grouptable.dao.GroupDAO;
import com.group3.basic.netcracker.backend.grouptable.entity.Group;
import com.group3.basic.netcracker.backend.grouptable.rowmapper.GroupRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GroupDaoImpl implements GroupDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createGroup(String name, int course_id) {
        String SQL = "INSERT INTO \"Group\" (name, course_id) VALUES (?,?)";

        jdbcTemplate.update(SQL, name, course_id);
    }

    @Override
    public Group getGroupById(int id) {
        String SQL = "SELECT * FROM \"Group\" WHERE id = ?";
        Group group = (Group) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new GroupRowMapper());
        return group;    }

    @Override
    public List listGroups() {
        String SQL = "SELECT * FROM \"Group\"";
        List groups = jdbcTemplate.query(SQL, new GroupRowMapper());
        return groups;
    }

    @Override
    public void removeGroup(int id) {
        String SQL = "DELETE FROM \"Group\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public void updateGroup(int id, String name, int course_id) {
        String SQL = "UPDATE \"User\" SET name = ?, course_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL, name, course_id, id);
    }
}
