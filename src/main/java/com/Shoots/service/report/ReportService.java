package com.Shoots.service.report;

import com.Shoots.domain.Report;

import java.util.List;

public interface ReportService {
    int insertReport(Report report);
    List<Report> selectReportedUsers(String reporter);

}
