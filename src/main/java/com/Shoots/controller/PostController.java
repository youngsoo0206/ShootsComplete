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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
//@CrossOrigin(origins = "http://localhost:1000")
@Controller
@RequestMapping(value="/post")
public class PostController {

    @Value("${my.savefolder}")
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

        // 페이지와 limit 값이 제대로 설정되었는지 확인
        int start = Math.max(0, (page - 1) * limit); // start가 음수일 수 없도록 Math.max 사용
        int end = limit; // 끝 값은 limit과 동일

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

        // 페이지와 limit 값이 제대로 설정되었는지 확인
        int start = Math.max(0, (page - 1) * limit); // start가 음수일 수 없도록 Math.max 사용
        int end = limit; // 끝 값은 limit과 동일

        int listcount = postService.getListCount(category);
        List<Post> list = postService.getPostList(page, limit, category);

        PaginationResult result = new PaginationResult(page, limit, listcount);
        result.setPostlist(list); // PaginationResult에 게시글 목록 추가
        return result; // 페이징 정보와 게시글 목록을 포함한 객체 반환
    }

//    /*
//     @ResponseBody를 사용하면 각 메서드의 실행 결과는 JSON으로 변환되어 HTTP Response BODY에
//    */
//    @ResponseBody
//    @PostMapping(value = "/list_ajax")
//    public Map<String, Object> postListAjax(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int limit
//    ) {
//        int listcount = postService.getListCount(); // 총 리스트 수를 받아옴
//
//        List<Post> list = postService.getPostList(page, limit);
//
//        PaginationResult result = new PaginationResult(page, limit, listcount);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("page", page);
//        map.put("maxpage", result.getMaxpage());
//        map.put("startpage", result.getStartpage());
//        map.put("endpage", result.getEndpage());
//        map.put("listcount", listcount);
//        map.put("postlist", list);
//        map.put("limit", limit);
//        return map;
//    }



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

        if (uploadfile != null && !uploadfile.isEmpty()) {
            String fileDBName = postService.saveUploadFile(uploadfile, saveFolder);
            post.setPost_file(fileDBName); //바뀐 파일명으로 저장
            post.setPost_original(uploadfile.getOriginalFilename()); // 원래 파일명 저장
        }

        if (post.getCategory() == null) {
            post.setCategory("A"); // 기본값 설정
        }

        if (post.getPrice() == null) {
            post.setPrice(0); // 기본값 설정
        }


        // 카테고리 처리
        if ("B".equals(post.getCategory())) {
            //post.setPrice(post.getPrice()); // 가격이 입력되었을 경우 유지
        } else {
            post.setPrice(0); // 기타 카테고리는 가격을 0으로 설정
        }

        postService.insertPost(post); // 저장메서드 호출

        logger.info("Post added: " + post.toString()); //selectKey로 정의한 BOARD_NUM 값 확인해 봅니다.
        return "redirect:list";
    }


    //detail?num=9요청시 파라미터 num의 값을 int num에 저장합니다.
    @GetMapping("/detail")
    public ModelAndView Detail(
            int num, ModelAndView mv,
            HttpServletRequest request,
            @RequestHeader(value = "referer", required = false) String beforeURL, HttpSession session) {
        /*
            1. String beforeURL = request.getHeader("referer"); 의미로
                어느 주소에서 detail로 이동했는지 header의 정보 중에서 "referer"를 통해 알 수 있습니다.
            2. 수정 후 이곳으로 이동하는 경우 조회수는 증가하지 않도록 합니다.
            3. myhome4/board/list에서 제목을 클릭한 경우 조회수가 증가하도록 합니다.
        */
        String sessionReferer = (String) session.getAttribute("referer");
        logger.info("referer:" + beforeURL);
        if (sessionReferer != null && sessionReferer.equals("list")) {
            if (beforeURL != null && beforeURL.endsWith("list")) {
                postService.setReadCountUpdate(num);
            }
            session.removeAttribute("referer");

        }

        Post post = postService.getDetail(num);
        //board=null; //error 페이지 이동 확인하고자 임의로 지정합니다.
        if (post == null) {
            logger.info("상세보기 실패");
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "상세보기 실패입니다.");
        } else {
            logger.info("상세보기 성공");
            //int count = commentService.getListCount(num);
            //int count = commentService.getListCount(num);
            // int count=0;
            mv.setViewName("post/post_view");
            //mv.addObject("count", count);
            mv.addObject("postdata", post);
        }
        return mv;
    }

    @GetMapping("/modifyView")
    public ModelAndView PostModifyView(
            int num,
            ModelAndView mv,
            HttpServletRequest request
    ) {
        Post postdata = postService.getDetail(num);

        //글 내용 불러오기 실패한 경우입니다.
        if (postdata == null) {
            logger.info("수정보기 실패");
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "수정보기 실패입니다.");
        } else {
            logger.info("(수정)상세보기 성공");
            //수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 boarddata 객체를
            //ModelAndView 객체에 저장합니다.
            mv.addObject("postdata", postdata);
            //글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
            mv.setViewName("post/post_modify");
        }
        return mv;
    }


    @PostMapping("/modifyAction")
    public String PostModifyAction(
            Post postdata,
            String check, Model mv,
            HttpServletRequest request,
            RedirectAttributes rattr
    ) throws Exception {
        boolean usercheck = postService.isPostWriter(postdata.getPost_idx());

        String url="";
        // 비밀번호가 다른 경우
        if (usercheck == false) {
            rattr.addFlashAttribute("message", "비밀번호 오류 입니다.");
            rattr.addFlashAttribute("url", "history.back()");
            return "redirect:/message";
        }

        //String url = "";
        MultipartFile uploadfile = postdata.getUploadfile();
        //String saveFolder = request.getSession().getServletContext().getRealPath("resources/upload");

        if (check != null && !check.equals("")) { //기존파일 그대로 사용하는 경우
            logger.info("기존파일 그대로 사용합니다.");
            postdata.setPost_original(check);
            //<input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_FILE}">
            //위 문장 때문에 board.setBOARD_FILE()값은 자동 저장됩니다.
        } else {
            //답변글의 경우 파일 첨부에 대한 기능이 없습니다.
            //만약 답변글을 수정할 경우
            //<input type="file" id="upfile" name="uploadfile" > 엘리먼트가 존재하지 않아
            //private MultipartFile uploadfile;에서 uploadfile은 null입니다.
            if (uploadfile != null && !uploadfile.isEmpty()) {
                logger.info("파일 변경되었습니다.");
                String fileDBName = postService.saveUploadFile(uploadfile, saveFolder);
                postdata.setPost_file(fileDBName);
                postdata.setPost_original(uploadfile.getOriginalFilename());
            } else { // 기존 파일이 없는데 파일 선택하지 않은 경우 또는 기존 파일이 있었는데 삭제한 경우
                logger.info("선택 파일 없습니다.");
                //<input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_FILE}">
                //위 태그에 값이 있다면 ""로 값을 변경합니다.
                postdata.setPost_file(""); // ""로 초기화 합니다.
                postdata.setPost_original(""); // ""로 초기화 합니다.
            } //else end
        } //else end

        // DAO에서 수정 메서드 호출하여 수정합니다.
        int result = postService.postModify(postdata);

        // 수정에 실패한 경우
        if (result == 0) {
            logger.info("게시판 수정 실패");
            mv.addAttribute("url", request.getRequestURL());
            mv.addAttribute("message", "게시판 수정 실패");
            url = "error/error";
        } else { // 수정 성공의 경우
            logger.info("게시판 수정 완료");
            //수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기 위해 경로를 설정합니다.
            url = "redirect:detail";
            rattr.addAttribute("num", postdata.getPost_idx());
        }
        return url;
    }



}
