package com.group3.basic.netcracker.backend.shedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group3.basic.netcracker.backend.course.Course;
import com.group3.basic.netcracker.backend.course.CourseDao;
import com.group3.basic.netcracker.backend.course.CourseRowMapper;

	@Transactional
	@Repository
	public class StudyScheduleDaoImpl implements StudyScheduleDao {
	    private final JdbcTemplate jdbcTemplate;

	    @Autowired
	    public StudyScheduleDaoImpl(JdbcTemplate jdbcTemplate){
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    @Override
	    public StudySchedule getStudyScheduleById(int id) {
	        String SQL = "SELECT * FROM \"StudySchedule\" WHERE id = ?";
	        StudySchedule shedule = (StudySchedule) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new StudyScheduleRowMapper());
	        return shedule;
	    }


	    @Override
	    public List listStudySchedule() {
	        String SQL = "SELECT * FROM \"StudySchedule\"";
	        List shedule = jdbcTemplate.query(SQL, new StudyScheduleRowMapper());
	        return shedule;
	    }

	    @Override
	    public void removeStudySchedule(int id) {
	        String SQL = "DELETE FROM \"StudySchedule\" WHERE id = ?";
	        jdbcTemplate.update(SQL, id);
	        System.out.println("StudySchedule removed");
	    }

	    @Override
	    public void updateStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen) {
	        String SQL = "UPDATE \"StudySchedule\" SET course_id = ?, time_slot_id = ?, is_choosen = ? WHERE id = ?";
	        jdbcTemplate.update(SQL, courseId, userId, timeSlotId, isChoosen);
	        System.out.println("StudySchedule updated.");
	    }
	    @Override
		public void createStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen) {
			 String SQL = "INSERT INTO \"StudySchedule\" (course_id, user_id, time_slot_id, is_choosen) VALUES (?,?,?,?)";
		        jdbcTemplate.update(SQL, courseId, userId, timeSlotId, isChoosen);
		        System.out.println("StudySchedule created.");
		}

	}
