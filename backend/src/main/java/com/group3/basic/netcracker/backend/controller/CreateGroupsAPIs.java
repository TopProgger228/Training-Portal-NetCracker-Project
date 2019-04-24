//package com.group3.basic.netcracker.backend.controller;
//
//
//import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
//import com.group3.basic.netcracker.backend.coursetable.courseservice.CourseService;
//import com.group3.basic.netcracker.backend.coursetable.entity.CourseForm;
//import com.group3.basic.netcracker.backend.grouptable.entity.GroupForm;
//import com.group3.basic.netcracker.backend.grouptable.groupservice.GroupService;
//import com.group3.basic.netcracker.backend.usertable.userservice.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.time.LocalDate;
//import java.util.List;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api")
//public class CreateGroupsAPIs {
//
//    private GroupService groupService;
//    private UserService userService;
//
//    @Autowired
//    public CreateGroupsAPIs(GroupService groupService, UserService userService) {
//        this.groupService = groupService;
//        this.userService = userService;
//    }
//
//
//
//    @GetMapping("/getstudents")
//    public List getTrainers(){
//        return userService.getStudents();
//    }
//
//    @PostMapping("/create_new_group")
//    public ResponseEntity<?> createNewGroup( @RequestBody GroupForm groupFrom){
//
//        groupService.createGroup(courseForm.getName(),LocalDate.parse(courseForm.getStart_date()),LocalDate.parse(courseForm.getEnd_date()),
//                courseForm.getInfo(),courseForm.getSkill_level(), courseForm.getLearn_direction(),
//                courseForm.getUser_id(),courseForm.getQty_per_week());
//
//        return new ResponseEntity<>(new ResponseMessage("Course created successfully!"), HttpStatus.CREATED);
//    }
//}
