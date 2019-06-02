package com.group3.basic.netcracker.backend.service;


public interface ReporterService {
    void createReportByCourse(int[] courses);

    void createReportByTrainer(String username);

    void createReportByStudent(String username);

    void createReportByLevel(String level);
}
