package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.LessonDto;
import com.group3.basic.netcracker.backend.service.LessonService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class LessonAPIs {

    private LessonService lessonService;

    @Autowired
    public LessonAPIs(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("getLessonByCourseId/{courseId}")
    public ResponseEntity<?> getLessonByCourseId(@PathVariable int courseId) {

        List<LessonDto> lessonDtoList = lessonService.getLessonsByCourseId(courseId);

        log.info("Received lessons of course with id - {}", courseId);

        return ResponseEntity.ok().body(lessonDtoList);

    }
}
