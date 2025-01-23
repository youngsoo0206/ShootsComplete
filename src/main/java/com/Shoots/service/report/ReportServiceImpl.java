package com.Shoots.service.report;

import com.Shoots.domain.Report;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private ReportMapper dao;

    @Override
    public int insertReport(Report report) {
        return dao.insertReport(report);
    }

    @Override
    public List<Report> selectReportedUsers(String reporter) {
        return dao.selectReportedUsers(reporter);
    }
}
