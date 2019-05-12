package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.entity.User;

import java.util.List;

public interface LessonMissingDao {

    List<LessonMissing> getLessonMissingByCourse (int courseId);

    List<LessonMissing> getLessonMissingByLesson (int lessonId);

    List<LessonMissing> getLessonMissingByUser (int userId);

    List<LessonMissing> getLessonMissingByTrainer (int trainerId);

    void add (int userId, int lessonId, String reason);

    void delete (int userId, int lessonId);

    void updateReason (int userId, int lessonId, String reason);

    int getMissingLessonCountByUserAndCourse (int courseId, int userId);
}
