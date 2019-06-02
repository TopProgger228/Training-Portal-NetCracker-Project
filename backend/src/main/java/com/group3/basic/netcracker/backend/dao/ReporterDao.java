package com.group3.basic.netcracker.backend.dao;

import java.util.List;
import java.util.Map;

public interface ReporterDao {
    List<Map<String, Object>> queryReportByTrainer(String username);

    List<Map<String, Object>> queryReportByCourse(int[] courses);

    List<Map<String, Object>> queryReportByStudent(String username);

    List<Map<String, Object>> queryReportByLevel(String level);
}
