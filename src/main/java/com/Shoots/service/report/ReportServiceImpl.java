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

    @Override
    public Report selectCheckReportDuplicate(String reporter,int PostIdx, int CommentIdx, String category) {
        return dao.selectCheckReportDuplicate(reporter, PostIdx, CommentIdx, category);
    }

    @Override
    public int selectReportedCount(String reported, String category) {
        return dao.selectReportedCount(reported, category);
    }

    @Override
    public List<Report> getReportList() {
        return dao.getReportList();
    }

    @Override
    public int getReportCount() {
        return dao.getReportCount();
    }
}
