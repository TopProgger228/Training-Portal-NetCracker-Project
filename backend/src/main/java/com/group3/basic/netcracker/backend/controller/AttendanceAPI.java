package com.group3.basic.netcracker.backend.controller;

import java.sql.SQLException;
import java.util.List;

import com.group3.basic.netcracker.backend.dto.*;
import com.group3.basic.netcracker.backend.report.Reporter;
import com.group3.basic.netcracker.backend.service.CheckAttendanceService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.basic.netcracker.backend.service.AttendanceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AttendanceAPI {
    private AttendanceService attendanceService;
    private CheckAttendanceService checkAttendanceService;
    private Reporter reporter;

    @Autowired
    public AttendanceAPI(AttendanceService attendanceService, CheckAttendanceService checkAttendanceService, Reporter reporter) {
        this.attendanceService = attendanceService;
        this.checkAttendanceService = checkAttendanceService;
        this.reporter = reporter;
    }

    @GetMapping("/attCourse")
    public ResponseEntity<?> getAllCourseAttendance() {

        List<CourseAttendanceDto> list = attendanceService.getAllCourseAttendance();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/attLesson/{id}")
    public ResponseEntity<?> getLessonsByCourse(@PathVariable int id) {

        List<LessonAttendanceDto> list = attendanceService.getLessonsOfCourseAttendance(id);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/attUser/{id}")
    public ResponseEntity<?> getUsersByLesson(@PathVariable int id) {

        List<UserAttendanceDto> list = attendanceService.getUsersOfCourseAttendance(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullAttCheck/{id}")
    public ResponseEntity<?> checkFullAttendance(@PathVariable int id) {

        CheckLessonAttendanceDto checkLessonAttendanceDto = checkAttendanceService.getFullCheckAttendance(id);
        return ResponseEntity.ok().body(checkLessonAttendanceDto);
    }

    @GetMapping("/trainerLesson/{id}")
    public ResponseEntity<?> getTrainerTodayLessons(@PathVariable int id) {

        List<LessonAttendanceDto> lessonAttendanceDtoList = checkAttendanceService.getTodayLessonsByTrainer(id);
        return ResponseEntity.ok().body(lessonAttendanceDtoList);
    }

    @PostMapping("/lessonAtt")
    public ResponseEntity<?> changeAttendance(@RequestParam("userId") String userId,
                                              @RequestParam("lessonId") String lessonId,
                                              @RequestParam("status") String status) {

        checkAttendanceService.changeLessonMissing(Integer.parseInt(userId), Integer.parseInt(lessonId), status);
        return new ResponseEntity<>(new ResponseMessage("Present"), HttpStatus.OK);

    }

    @GetMapping("getAllTrainer")
    public ResponseEntity<?> getAllTrainer() {

        List<TrainerSelectorDto> list = attendanceService.getTrainerForSelector();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseByUser/{username}")
    public ResponseEntity<?> getCourseByUser(@PathVariable String username) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUser(username);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseByTrainer/{id}")
    public ResponseEntity<?> getCourseByTrainer(@PathVariable int id) {

        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByTrainer(id);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseBySkillLevel/{level}")
    public ResponseEntity<?> getCourseBySkillLevel(@PathVariable String level) {

        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceBySkillLevel(level);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getTrainerLesson/{userName}")
    public ResponseEntity<?> getTrainerTodayLessonsUsername(@PathVariable String userName) {

        List<LessonAttendanceDto> lessonAttendanceDtoList = checkAttendanceService.getTodayLessonsByTrainerUsername(userName);
        return ResponseEntity.ok().body(lessonAttendanceDtoList);
    }

    @GetMapping("/getManagerUserAtt/{userName}")
    public ResponseEntity<?> getStudentAttendanceForManagerByUsername(@PathVariable String userName) {

        List<StudentAttendanceForManagerDto> studentAttendanceForManagerDtoList = attendanceService.getStudentAttendanceForManagerDto(userName);
        return ResponseEntity.ok().body(studentAttendanceForManagerDtoList);
    }


    @GetMapping("getCourseByUserId/{userId}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable int userId) {

        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUserId(userId);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getCourseLessonsForOneUser/{courseId}/{userId}")
    public ResponseEntity<?> getLessonsByCourseUser(@PathVariable int courseId, @PathVariable int userId) {
        List<LessonAttendanceDto> list = attendanceService.getLessonsOfCourseAttendanceByUser(courseId, userId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("createReport/course")
    public ResponseEntity<?> createReportByCourse(@RequestParam("courses") int[] courses) {
        try {
            reporter.createReportByCourse(courses);
            return new ResponseEntity<>(new ResponseMessage("Report on selected courses saved"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Error! Can not create report!"), HttpStatus.CONFLICT);
        }

    }

    @PostMapping("createReport/trainer")
    public ResponseEntity<?> createReportByTrainer(@RequestParam("trainerId") int trainerId) {
        try {
            reporter.createReportByTrainer(trainerId);
            return new ResponseEntity<>(new ResponseMessage("Report on selected trainer saved"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Error! Can not create report!"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("createReport/student")
    public ResponseEntity<?> createReportByStudent(@RequestParam("username") String username) {
        try {
            reporter.createReportByStudent(username);
            return new ResponseEntity<>(new ResponseMessage("Report on selected user saved"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Error! Can not create report!"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("createReport/level")
    public ResponseEntity<?> createReportByLevel(@RequestParam("level") String level) {
        try {
            reporter.createReportByLevel(level);
            return new ResponseEntity<>(new ResponseMessage("Report on selected level saved"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Error! Can not create report!"), HttpStatus.CONFLICT);
        }

    }


}