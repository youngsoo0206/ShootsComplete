package com.Shoots.service.report;

import com.Shoots.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    public int insertReport(Report report);
    public List<Report> selectReportedUsers(String reporter);
    public Report selectCheckReportDuplicate(String reporter,int PostIdx, int CommentIdx,String category);
    public int selectReportedCount(String reported,String category);
    public List<Report> getReportList(); //report list 가져오기
    public int getReportCount(); //총 신고 개수
}
