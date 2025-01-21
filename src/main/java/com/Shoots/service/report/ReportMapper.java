package com.Shoots.service.report;

import com.Shoots.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    public int insertReport(Report report);
    public List<Report> selectReportedUsers(String reporter);
}
