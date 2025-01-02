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
        map.put("start", startrow);
        map.put("end", endrow);
        map.put("category", category); // 카테고리 추가

        return dao.getPostList(map); // 카테고리를 포함한 게시글 목록 조회
    }



    @Override
    public String saveUploadFile(MultipartFile uploadfile, String saveFolder) throws Exception {
        String originalFilename = uploadfile.getOriginalFilename();
        String fileDBName = fileDBName(originalFilename, saveFolder);

        // 파일 저장
        uploadfile.transferTo(new java.io.File(saveFolder + fileDBName));
        return fileDBName;
    }

    @Override
    public String fileDBName(String fileName, String saveFolder) {
        String dateFolder = createFolderByDate(saveFolder);
        String fileExtension = getFileExtension(fileName);
        String refileName = generateUniqueFileName(fileExtension);
        return java.io.File.separator + dateFolder + java.io.File.separator + refileName;
    }

    @Override
    public int[] getCurrentDate() {
        java.time.LocalDate now = java.time.LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int date = now.getDayOfMonth();

        return new int[]{year, month, date};
    }

    @Override
    public String createFolderByDate(String baseFolder) {
        int[] currentDate = getCurrentDate();
        int year = currentDate[0];
        int month = currentDate[1];
        int date = currentDate[2];

        String dateFolder = year + "-" + month + "-" + date;
        String fullFolderPath = baseFolder + java.io.File.separator + dateFolder;
        java.io.File path = new java.io.File(fullFolderPath);

        if (!path.exists()) {
            path.mkdirs();
        }
        return dateFolder;
    }

    @Override
    public String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return (index > 0) ? fileName.substring(index + 1) : "";
    }

    @Override
    public String generateUniqueFileName(String fileExtension) {
        int[] currentDate = getCurrentDate();
        int year = currentDate[0];
        int month = currentDate[1];
        int date = currentDate[2];

        Random r = new Random();
        int random = r.nextInt(100000000);

        return "bbs" + year + month + date + random + "." + fileExtension;
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
