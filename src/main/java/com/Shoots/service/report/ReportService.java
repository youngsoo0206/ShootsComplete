package com.Shoots.service.report;

import com.Shoots.domain.Report;

import java.util.List;

public interface ReportService {
    int insertReport(Report report);
    List<Report> selectReportedUsers(String reporter);
    public Report selectCheckReportDuplicate(String reporter,String reported,String category);
    public int selectReportedCount(String reported,String category);
}
