package com.Shoots.controller;

import com.Shoots.domain.Faq;
import com.Shoots.service.FaqService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/testAdmin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Value("{my.savefolder}")
    private String saveFolder;

    private FaqService faqService;

    public AdminController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public String admin(){
        return "admin/admin";
    }

    //faq 리스트
    @GetMapping(value="/faqList")
    public ModelAndView faqList(
            ModelAndView mv){
        int listcount = faqService.getListCount();
        List<Faq> list = faqService.getFaqList();

        mv.setViewName("admin/faqList");
        mv.addObject("list", list);
        mv.addObject("listcount", listcount);
        return mv;
    }

    //상세보기
    @GetMapping(value="/faqDetail")
    public ModelAndView faqDetail(int id, ModelAndView mv, HttpServletRequest request){
        Faq faq = faqService.faqDetail(id);
        if(faq == null){
            logger.info("자세히 보기 실패");
        } else{
            mv.setViewName("admin/faqDetail");
            mv.addObject("faq", faq);
        }
        return mv;
    }

    //글쓰기 페이지
    @GetMapping(value="/faqWrite")
    public String faqWrite(){
        return "admin/faqWrite";
    }

    //추가
    @PostMapping("/faqAdd")
    public String faqAdd(Faq faq, HttpServletRequest request) throws Exception{

        MultipartFile uploadfile = faq.getUploadfile();
        if(!uploadfile.isEmpty()){
            String fileDBName = faqService.saveUploadFile(uploadfile, saveFolder);
            faq.setFaq_file(fileDBName);    //바뀐 파일명으로 저장
            faq.setFaq_original(uploadfile.getOriginalFilename());  //원래 파일명 저장
        }
        faqService.insertFaq(faq);

        return "redirect:";
    }

    //수정 폼
    @GetMapping(value="/faqUpdate")
    public ModelAndView faqUpdate(int id, HttpServletRequest request, ModelAndView mv) {
        Faq faq = faqService.faqDetail(id);
        if(faq == null){
            logger.info("수정 보기 실패");
        } else{
            logger.info("수정보기 성공");
            mv.setViewName("admin/faqUpdate");
            mv.addObject("faq", faq);
        }
        return mv;
    }

    //수정
    @PostMapping(value="/faqUpdateProcess")
    public String faqUpdateProcess(Faq faq, Model model, HttpServletRequest request, RedirectAttributes rattr){
        int result = faqService.updateFaq(faq);
        if(result == 1){
            rattr.addFlashAttribute("result", "updateSuccess");
            return "redirect:/faqList";
        }else{
            //model.addAttribute("url", request.getRequestURL());
            return "redirect:"; // error/error
        }
    }

    //삭제
    @GetMapping(value="/faqDelete")
    public String faqDelete(int id){
        faqService.deleteFaq(id);
        return "redirect:";
    }

    //다운

}
