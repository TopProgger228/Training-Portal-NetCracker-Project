package com.group3.basic.netcracker.backend.util.schedulefunction;

import java.sql.*;

public class ScheduleGenerate {
    private final String url = "jdbc:postgresql://178.128.252.215/training";
    private final String user = "deploy";
    private final String password = "secret";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //Method selects the most popular timeslots and generates a schedule by sql function
    public int generateSchedule(int course) {
        int result = 1;
        try (Connection conn = this.connect();
             CallableStatement callSchedule = conn.prepareCall("{ ? = call schedule_setter( ? ) }")) {
            callSchedule.registerOutParameter(1, Types.INTEGER);
            callSchedule.setInt(2, course);
            callSchedule.execute();
            result = callSchedule.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Schedule for course " + course + " has been choosen");
        return result;
    }


}
