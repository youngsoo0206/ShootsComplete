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
import java.util.Map;

@Controller
@AllArgsConstructor
public class ReportController {

    BoardService boardService;
    ReportService reportService;
    private static Logger logger = (Logger) LoggerFactory.getLogger(ReportController.class);

    @GetMapping("/report")
    public String location(Model model, HttpSession session) {
        model.addAttribute("boardList", boardService.selectBoardList());
        logger.info("session 값 : " + session.getAttribute("id"));
        //login 안하면 null이기 때문에 처리 필요
        model.addAttribute("reportList", reportService.selectReportedUsers(String.valueOf(session.getAttribute("id"))));
        return "report/reportList";
    }

    @PostMapping("/insertReport")
    @ResponseBody
    public Map<String, Object> insertReport(Model model, HttpSession session, @RequestBody Report report) {

        report.setReporterUser((String) session.getAttribute("id"));
        logger.info("insertReport 정보 : "+String.valueOf(report));
        logger.info("insertReport 실행결과 : "+String.valueOf(reportService.insertReport(report)));
        Map<String, Object> resp = new HashMap<>();
        if(reportService.insertReport(report) == 1)
            resp.put("msg", "신고가 접수되었습니다.");
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
