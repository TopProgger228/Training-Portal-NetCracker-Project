package com.group3.basic.netcracker.backend.dao.impl;

import java.util.List;

import com.group3.basic.netcracker.backend.util.sql.AttendanceDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.group3.basic.netcracker.backend.dao.AttendanceDao;
import com.group3.basic.netcracker.backend.util.rowmapper.AttendanceRowMapper;

@Transactional
@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttendanceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List listAttendance() {
        return jdbcTemplate.query(AttendanceDaoQueries.getAll, new AttendanceRowMapper());
    }
}
