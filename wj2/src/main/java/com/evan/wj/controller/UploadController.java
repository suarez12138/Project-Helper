package com.evan.wj.controller;

import com.evan.wj.result.Message_return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    @CrossOrigin
    @PostMapping("/vue-element-admin/student/project/upload_file")
    @ResponseBody
    public Message_return upload(MultipartFile file) {

        System.out.print("aaabbbccc");
        if (file.isEmpty()) {
            return new Message_return(20000,"Failed! Please select the file!");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "S:\\For_git\\Project-Helper\\store\\";
        String save_path = filePath + fileName;



        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return new Message_return(20000,"Success!");
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return new Message_return(20000,"Failed!");
    }
//
//    @PostMapping("/multiUpload")
//    @ResponseBody
//    public String multiUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        String filePath = "/Users/itinypocket/workspace/temp/";
//        for (int i = 0; i < files.size(); i++) {
//            MultipartFile file = files.get(i);
//            if (file.isEmpty()) {
//                return "上传第" + (i++) + "个文件失败";
//            }
//            String fileName = file.getOriginalFilename();
//
//            File dest = new File(filePath + fileName);
//            try {
//                file.transferTo(dest);
//                LOGGER.info("第" + (i + 1) + "个文件上传成功");
//            } catch (IOException e) {
//                LOGGER.error(e.toString(), e);
//                return "上传第" + (i++) + "个文件失败";
//            }
//        }
//
//        return "上传成功";
//
//    }
}








