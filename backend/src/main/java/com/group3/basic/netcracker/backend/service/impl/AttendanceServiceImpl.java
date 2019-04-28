package com.group3.basic.netcracker.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.group3.basic.netcracker.backend.dao.AttendanceDao;
import com.group3.basic.netcracker.backend.entity.Attendance;
import com.group3.basic.netcracker.backend.service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	private final AttendanceDao attendanceDao;

	@Autowired
	public AttendanceServiceImpl(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	@Override
	public List<Attendance> listAttendance() {
         return attendanceDao.listAttendance();
	}

}
