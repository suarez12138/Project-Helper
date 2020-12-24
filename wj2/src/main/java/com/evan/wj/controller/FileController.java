package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.wky_String_String;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.wky_uploadDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FileController {
    @Autowired
    wky_uploadDAO upDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/file/all_file")
    @ResponseBody
    public TempleteResult<wky_String_String> getAllGroup(){
        List<wky_String_String> sub1 = upDAO.getAllFile();
        TempleteResult<wky_String_String> allProjectResult_t = new TempleteResult<wky_String_String>(20000,sub1);
        return allProjectResult_t;
    }
}
