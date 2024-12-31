package com.Shoots.service;

import com.Shoots.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public interface PostService {

    //글의 갯수 구하기
    public int getListCount(String category);

    // 글 목록 보기
    public List<Post> getPostList(int page, int limit, String category);



    default public String saveUploadFile(MultipartFile uploadfile, String saveFolder) throws Exception {
        String originalFilename = uploadfile.getOriginalFilename();
        String fileDBName = fileDBName(originalFilename, saveFolder);

        //파일 저장
        uploadfile.transferTo(new File(saveFolder + fileDBName));

        return fileDBName;
    }

    default public String fileDBName(String fileName, String saveFolder) {
        String dateFolder = createFolderByDate(saveFolder);
        String fileExtension = getFileExtension(fileName);
        String refileName = generateUniqueFileName(fileExtension);
        return File.separator + dateFolder + File.separator + refileName;
    }

    default public int[] getCurrentDate() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int date = now.getDayOfMonth();

        return new int[]{year, month, date};
    }


    default public String createFolderByDate(String baseFolder) {
        int[] cuurentDate = getCurrentDate();
        int year = cuurentDate[0];
        int month = cuurentDate[1];
        int date = cuurentDate[2];

        String dateFolder = year + "-" + month + "-" + date;
        String fullFolderPath = baseFolder + File.separator + dateFolder;
        File path = new File(fullFolderPath);

        if(!path.exists()) {
            path.mkdirs();
        }
        return dateFolder;
    }

    default public String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return (index > 0) ? fileName.substring(index+1) : "";
    }

    default public String generateUniqueFileName(String fileExtension) {
        int[] currentDate = getCurrentDate();
        int year = currentDate[0];
        int month = currentDate[1];
        int date = currentDate[2];

        Random r = new Random();
        int random = r.nextInt(100000000);

        return "bbs" + year + month + date + random + "." + fileExtension;
    }

    // 글 등록하기
    void insertPost(Post post);

    void setReadCountUpdate(int num);

}
