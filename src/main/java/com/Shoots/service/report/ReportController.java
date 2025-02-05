package com.Shoots.service.report;

import com.Shoots.domain.Board;
import com.Shoots.domain.Report;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ReportController {

    BoardService boardService;
    ReportService reportService;
    private static Logger logger = (Logger) LoggerFactory.getLogger(ReportController.class);
    @GetMapping("/report")
    public String location(Model model, HttpSession session) {

        //신고대상 제거 로직
        List<Board> boardList = boardService.selectBoardList();
        List<Report> reportList = reportService.selectReportedUsers(String.valueOf(session.getAttribute("id"))); // 신고자 == userId 인 report정보 가져옴
        Iterator<Board> iterator = boardList.iterator();
        while (iterator.hasNext() && reportList.size() > 0) {
            Board board = iterator.next();
            for(Report report : reportList) {
                if(board.getWriter().equals(report.getReportedUser())){
                    iterator.remove();
                    break;
                }
            }
        }

        model.addAttribute("boardList", boardList);
        logger.info("session 값 : " + session.getAttribute("id"));
        return "report/reportList";
    }

    @PostMapping("/insertReport")
    @ResponseBody
    public Map<String, Object> insertReport(Model model, HttpSession session, @RequestBody Report report) {

        Map<String, Object> resp = new HashMap<>();
        String reporter = String.valueOf(session.getAttribute("id"))    ;

        if(report.getCategory().equals("COMMENT")){
            //logger.info("CommentIdx 타입: " + report.getCommentIdx().getClass().getSimpleName());
            logger.info(" Post idx  : " + report.getPostIdx());
            logger.info(" comment idx  : " + report.getCommentIdx());
        }
        if(session.getAttribute("id") == null) {
            resp.put("msg", "로그인 후 이용해주세요.");
            return resp;
        }

        report.setReporter((String) session.getAttribute("id"));
        logger.info("insertReport 정보 : "+String.valueOf(report));

        if(reportService.selectCheckReportDuplicate(report.getReporter(), report.getPostIdx(), report.getCommentIdx(), report.getCategory()) != null)
            resp.put("msg", "이미 접수된 신고입니다.");
        else if(reportService.insertReport(report) == 1){
            resp.put("msg", report.getReportedUser() + "님의 신고가 접수되었습니다.");
            resp.put("reportCnt", reportService.selectReportedCount(report.getReportedUser(), report.getCategory()));
        }
        else
            resp.put("msg", "신고에 실패했습니다.");
        return resp;
    }

    @GetMapping("/boardView")
    public String view(@RequestParam int num, Model model) {
        model.addAttribute("board", boardService.selectByBoardNum(num));
        return "report/reportView";
    }


    @GetMapping("/report2")
    public ModelAndView location2(@RequestParam(defaultValue = "1") int page, ModelAndView mv, HttpSession session) {
        mv.setViewName("report/post2");
        return mv;
    }

    @GetMapping(value = "/report3")
    public String add(Board board, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        redirectAttributes.addAttribute("board", "im model");
        boardService.insertBoard(board); // 저장 메서드 호출
        return "redirect:report2";
    }
}
