package com.Shoots.controller;

import com.Shoots.domain.PaginationResult;
import com.Shoots.domain.Post;
import com.Shoots.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//@RestController
//@CrossOrigin(origins = "http://localhost:1000")
@Controller
@RequestMapping(value="/post")
public class PostController {

    //@Value("${my.savefolder}")
    private String saveFolder;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private PostService postService;

    //클래스에 생성자가 하나만 존재하는 경우 Spring이 자동으로 의존성을 주입해 주므로 @Autowired를 붙일 필요가 없습니다.
    //Spring Boot 2.6 이상에서는 생성자가 하나뿐인 경우 @Autowired를 생략하는 것을 권장합니다.
    //생성자 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }



    @GetMapping(value = "/list")
    public ModelAndView postlist(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "A") String category, // 기본 카테고리 추가
            ModelAndView mv,
            HttpSession session) {

        session.setAttribute("referer", "list");
        int limit = 10; // 한 화면에 출력할 로우 갯수

        int listcount = postService.getListCount(category); // 총 리스트 수를 받아옴
        List<Post> list = postService.getPostList(page, limit, category); // 리스트를 받아옴

        PaginationResult result = new PaginationResult(page, limit, listcount);

        mv.setViewName("post/post_list");
        mv.addObject("page", page);
        mv.addObject("maxpage", result.getMaxpage());
        mv.addObject("startpage", result.getStartpage());
        mv.addObject("endpage", result.getEndpage());
        mv.addObject("listcount", listcount);
        mv.addObject("postlist", list);
        mv.addObject("limit", limit);
        mv.addObject("pagination", result); // PaginationResult 객체를 전달
        return mv;
    }

    // AJAX 요청을 처리하여 게시글 목록 반환
    @GetMapping(value = "/list/ajax")
    @ResponseBody
    public PaginationResult postListAjax(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "A") String category // 기본값 추가
    ) {
        int listcount = postService.getListCount(category);
        List<Post> list = postService.getPostList(page, limit, category);

        PaginationResult result = new PaginationResult(page, limit, listcount);
        result.setPostlist(list); // PaginationResult에 게시글 목록 추가
        return result; // 페이징 정보와 게시글 목록을 포함한 객체 반환
    }



    //글쓰기
    @GetMapping(value = "/write")// /board/write
    public String postWrite() {
        return "post/post_write";
    }


    /*
    스프링 컨테이너는 매개변수Board객체를 생성하고 Board객체의 setter 메서드들을 호출하여
    사용자 입력값을 설정합니다.
    매개변수의 이름과 setter의 property가 일치하면 됩니다.
    */
    @PostMapping("/add")
    public String add(Post post, HttpServletRequest request)
            throws Exception {

        //String saveFolder = request.getSession().getServletContext().getRealPath("resources/upload");
        MultipartFile uploadfile = post.getUploadfile();

        if (!uploadfile.isEmpty()) {
            String fileDBName = postService.saveUploadFile(uploadfile, saveFolder);
            post.setPost_file(fileDBName); //바뀐 파일명으로 저장
        }

        postService.insertPost(post); // 저장메서드 호출
        logger.info(post.toString()); //selectKey로 정의한 BOARD_NUM 값 확인해 봅니다.
        return "redirect:list";
    }


}
