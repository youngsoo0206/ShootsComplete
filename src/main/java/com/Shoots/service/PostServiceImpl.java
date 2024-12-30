package com.Shoots.service;

import com.Shoots.domain.Post;
import com.Shoots.mybatis.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostMapper dao;
    public PostServiceImpl(PostMapper dao) {
        this.dao = dao;
    }

    @Override
    public int getListCount() {
        return dao.getListCount();
    }

    @Override
    public List<Post> getPostList(int page, int limit) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int startrow = (page - 1) * limit+1;
        int endrow = startrow + limit-1;
        map.put("start", startrow);
        map.put("end", endrow);
        return dao.getPostList(map);
    }

    @Override
    public String saveUploadFile(MultipartFile uploadfile, String saveFolder) throws Exception {
        return PostService.super.saveUploadFile(uploadfile, saveFolder);
    }

    @Override
    public String fileDBName(String fileName, String saveFolder) {
        return PostService.super.fileDBName(fileName, saveFolder);
    }

    @Override
    public int[] getCurrentDate() {
        return PostService.super.getCurrentDate();
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




}
