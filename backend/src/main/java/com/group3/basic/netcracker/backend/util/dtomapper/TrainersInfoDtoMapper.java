package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.TrainersInfoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainersInfoDtoMapper implements RowMapper {
    @Override
    public TrainersInfoDto mapRow(ResultSet row, int rowNum) throws SQLException {
        TrainersInfoDto trainersInfo = new TrainersInfoDto();

        trainersInfo.setId(row.getInt("id"));
        trainersInfo.setFname(row.getString("fname"));
        trainersInfo.setLname(row.getString("lname"));
        trainersInfo.setInfo(row.getString("info"));

        return trainersInfo;
    }
}
