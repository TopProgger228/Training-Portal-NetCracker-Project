package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.dto.LessonDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.service.LessonService;
import com.group3.basic.netcracker.backend.util.dtomapper.LessonDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;
    private final LessonDtoMapper lessonDtoMapper;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao, LessonDtoMapper lessonDtoMapper) {
        this.lessonDao = lessonDao;
        this.lessonDtoMapper = lessonDtoMapper;
    }


    @Override
    public List<LessonDto> getLessonsByCourseId(int courseId) {

        List<LessonDto>lessonDtoList = new ArrayList<>();
        List<Lesson> lessonList = lessonDao.getLessonByCourse(courseId);

        for (Lesson lesson : lessonList) {
            lessonDtoList.add(lessonDtoMapper.toLessonDto(lesson));
        }

        return lessonDtoList;
    }
}
