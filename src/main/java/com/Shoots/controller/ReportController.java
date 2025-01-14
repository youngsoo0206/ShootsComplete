package com.Shoots.controller;

import com.Shoots.domain.Board;
import com.Shoots.service.report.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    BoardService boardService;

    @GetMapping("/report")
    public String location(Model model) {
        return "report/post1";
    }

    @GetMapping("/report2")
    public ModelAndView location2(@RequestParam(defaultValue = "1") int page, ModelAndView mv, HttpSession session) {
        mv.setViewName("report/post2");
        return mv;
    }

    @GetMapping(value = "/report3")
    public String add(Board board, HttpServletRequest request) throws Exception {
        //String saveFolder = request.getSession().getServletContext().getRealPath("resources/upload");
        MultipartFile uploadfile = board.getUploadfile();

        boardService.insertBoard(board); // 저장 메서드 호출
        logger.info(board.toString()); // selectKey 로 정의한 Board_Num 값 확인해 봅니다.
        return "redirect:list";
    }
}
