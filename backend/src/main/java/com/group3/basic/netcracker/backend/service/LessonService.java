package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.LessonDto;
import com.group3.basic.netcracker.backend.entity.Lesson;

import java.util.List;

public interface LessonService {

    List<LessonDto> getLessonsByCourseId (int courseId);

}


