package com.group3.basic.netcracker.backend.util.sql;

public interface TimeSlotDaoQueries {
    String getTimeSlotByIdQuery = "SELECT * FROM \"TimeSlot\" WHERE id = ?";

    String findByStartTimeQuery = "SELECT * FROM \"TimeSlot\" WHERE start_time = ?";

    String getTimeSlotsListQuery = "SELECT id, start_time, end_time, week_day, course_id FROM \"TimeSlot\"";

    String removeTimeSlotQuery = "DELETE FROM \"TimeSlot\" WHERE id = ?";

    String updateTimeSlotQuery = "UPDATE \"TimeSlot\" SET start_time = ?, end_time = ?, " +
            "week_day = ?, course_id = ? WHERE id = ?";

    String getTimeSlotByLessonIdQuery = "SELECT ts.id, ts.start_time, ts.end_time, ts.week_day, ts.course_id " +
            "FROM \"TimeSlot\" ts join \"Lesson\" l on ts.id = l.time_slot_id where l.id = ?";

    String createTimeSlotQuery = "INSERT INTO \"TimeSlot\" (start_time, end_time, week_day, course_id) " +
            "VALUES (?,?,?, ?)";

    String isTimeSlotWitAllRow = "SELECT COUNT(*) FROM \"TimeSlot\" WHERE (start_time = ? AND end_time = ?\n" +
            "AND week_day = ? AND course_id = ?)";
}
