package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.TagDAO;

import com.evan.wj.result.TempleteResult;
import com.evan.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class file_receive_testController {

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/img")


    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "C:\\Users\\wukaiyue\\Documents\\GitHub\\Project-Helper\\wj2\\src\\main\\java\\com\\evan\\wj\\receive_files";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
