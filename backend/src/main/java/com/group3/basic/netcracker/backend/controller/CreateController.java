package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.dto.CourseForm;
import com.group3.basic.netcracker.backend.dto.ScheduleForm;
import com.group3.basic.netcracker.backend.dto.TimeSlotForm;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class CreateController {

    private CourseService courseService;
    private UserService userService;
    private TimeSlotService timeSlotService;
    private ScheduleService scheduleService;

    @Autowired
    public CreateController(CourseService courseService, UserService userService, TimeSlotService timeSlotService,
                            ScheduleService scheduleService) {
        this.courseService = courseService;
        this.userService = userService;
        this.timeSlotService = timeSlotService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create_new_course")
    public ResponseEntity<?> createNewCourse(@RequestBody CourseForm courseForm) {

        courseService.createCourse(courseForm.getName(), LocalDate.parse(courseForm.getStart_date()),
                LocalDate.parse(courseForm.getEnd_date()),
                courseForm.getInfo(), courseForm.getSkill_level(),
                courseForm.getTrainer_id(), courseForm.getQty_per_week());

        log.info("New course created with name - {}", courseForm.getName());

        return new ResponseEntity<>(new ResponseMessage("Course created successfully!"), HttpStatus.CREATED);
    }

    @GetMapping("/courses-list")
    public List getCourses() {
        return courseService.listCourses();
    }

    @PostMapping("/create_new_timeslot")
    public ResponseEntity<?> createNewTimeSlot(@Valid @RequestBody TimeSlotForm timeSlotForm) {

        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter();

        if (timeSlotService.isTimeslotExists(LocalTime.parse(timeSlotForm.getStart_time(), parseFormat),
                LocalTime.parse(timeSlotForm.getEnd_time(), parseFormat), timeSlotForm.getWeek_day(),
                timeSlotForm.getCourse_id())) {

            log.warn("Timeslot exists!");

            return new ResponseEntity<>(new ResponseMessage("Timeslot exists!"), HttpStatus.BAD_REQUEST);
        } else {
            timeSlotService.createTimeSlot(LocalTime.parse(timeSlotForm.getStart_time(), parseFormat),
                    LocalTime.parse(timeSlotForm.getEnd_time(), parseFormat), timeSlotForm.getWeek_day(),
                    timeSlotForm.getCourse_id());

            log.info("New timeslot created!");

            return new ResponseEntity<>(new ResponseMessage("Timeslot created successfully!"), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/student/my-schedule/delete")
    public ResponseEntity<?> deleteMySchedule(@RequestParam int id) {
        scheduleService.removeSchedule(id);

        log.info("Schedule with id - {} is deleted", id);

        return new ResponseEntity<>(new ResponseMessage("Deleted!"), HttpStatus.OK);
    }

    @PostMapping("/create_new_schedule")
    public ResponseEntity<?> createNewSchedule(@RequestBody ScheduleForm scheduleForm) {

        if (scheduleForm.getTime_slot_id().length == 0) {
            log.debug("Schedule wasn't chosen");
            return new ResponseEntity<>(new ResponseMessage("Schedule wasn't chosen"), HttpStatus.BAD_REQUEST);
        }

        Integer[] tempReturnArray = scheduleService.isScheduleExists(scheduleForm.getUser_id(),
                scheduleForm.getTime_slot_id(), scheduleForm.isIs_choosen());

        if (tempReturnArray == null) {
            log.debug("All schedules already exists");
            return new ResponseEntity<>(new ResponseMessage("All schedules already exists"), HttpStatus.OK);
        } else {
            log.info("Schedule created successfully!");
            scheduleService.createSchedule(scheduleForm.getUser_id(), tempReturnArray, scheduleForm.isIs_choosen());
        }

        return new ResponseEntity<>(new ResponseMessage("Schedule created successfully!"), HttpStatus.OK);
    }
}