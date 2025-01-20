package com.Shoots.service.report;

import com.Shoots.domain.Board;
import com.Shoots.domain.Report;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReportController {

    BoardService boardService;
    ReportService reportService;

    @GetMapping("/report")
    public String location(Model model) {
        model.addAttribute("boardList", boardService.selectBoardList());
        model.addAttribute("report", reportService.selectReportedUsers("ReporterName"));
        return "report/reportList";
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
