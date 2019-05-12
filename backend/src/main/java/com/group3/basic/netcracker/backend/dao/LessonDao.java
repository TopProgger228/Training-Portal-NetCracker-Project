package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> getLessonByCourse(int courseId);

    Lesson getLessonById(int lessonId);

    List<Lesson> getTodayLessonsByTrainer(int trainerId);

    List<Lesson> getTodayLessonsByTrainerUsername(String username);

    int getLessonCountTillTodayByStudent(int userId);

    int getLessonCountInCourseTillToday(int courseId);

    int getLessonCountInCourse(int courseId);


}
