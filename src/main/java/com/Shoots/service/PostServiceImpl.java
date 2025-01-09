package com.Shoots.service;

import com.Shoots.domain.Post;
import com.Shoots.mybatis.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class PostServiceImpl implements PostService {
    private final PostMapper dao;

    public PostServiceImpl(PostMapper dao) {
        this.dao = dao;
    }

    @Override
    public int getListCount(String category) {
        return dao.getListCount(category); // 카테고리별 게시글 개수 조회
    }

    @Override
    public List<Post> getPostList(int page, int limit, String category) {
        // 페이지네이션을 위한 start와 end 계산
        HashMap<String, Object> map = new HashMap<>();
        int startrow = (page - 1) * limit + 1;
        int endrow = startrow + limit - 1;
        int offset = (page - 1) * limit;
        map.put("offset", offset);
        int pageSize = limit;
        map.put("pageSize", pageSize);



        map.put("start", startrow);
        map.put("end", endrow);
        map.put("category", category); // 카테고리 추가

        return dao.getPostList(map); // 카테고리를 포함한 게시글 목록 조회
    }

    @Override
    public List<Post> getAdminPostList(int page, int limit) {
        // 페이지네이션을 위한 start와 end 계산
        HashMap<String, Object> map = new HashMap<>();
        int startrow = (page - 1) * limit + 1;
        int endrow = startrow + limit - 1;
        int offset = (page - 1) * limit;
        map.put("offset", offset);
        int pageSize = limit;
        map.put("pageSize", pageSize);



        map.put("start", startrow);
        map.put("end", endrow);
        return dao.getAdminPostList(map);
    }


    @Override
    public String saveUploadFile(MultipartFile uploadfile, String saveFolder) throws Exception {
        return PostService.super.saveUploadFile(uploadfile, saveFolder);
    }

    @Override
    public String fileDBName(String fileName, String saveFolder) {return PostService.super.fileDBName(fileName, saveFolder);
    }

    @Override
    public int[] getCurrentDate() {return PostService.super.getCurrentDate();
    }

    @Override
    public String createFolderByDate(String baseFolder) {
        return PostService.super.createFolderByDate(baseFolder);
    }

    @Override
    public String getFileExtension(String fileName) {
        return PostService.super.getFileExtension(fileName);
    }

    @Override
    public String generateUniqueFileName(String fileExtension) {
        return PostService.super.generateUniqueFileName(fileExtension);
    }

    @Override
    public void insertPost(Post post) {
        dao.insertPost(post);
    }

    @Override
    public void setReadCountUpdate(int num) {
        dao.setReadCountUpdate(num);
    }

    @Override
    public Post getDetail(int num) {
        return dao.getDetail(num);
    }


    public boolean isPostWriter(int num) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("num", num);
        //map.put("post_idx", post_idx);
        Post result = dao.isPostWriter(map);
        return result != null; // result가 null이면 false, null이 아니면 true 리턴합니다.
    }

    @Override
    public int postModify(Post modifypost) {
        return dao.postModify(modifypost);
    }


    @Override
    public int postDelete(int num) {
        int result = 0;
        Post post = dao.getDetail(num);
        if(post != null) {
            result = dao.postDelete(post);
        }
        return result;
    }

    @Override
    public List<String> getDeleteFileList() {
        return dao.getDeleteFileList();
    }

    @Override
    public void deleteFileList(String filename) {
        dao.deleteFileList(filename);
    }

}
