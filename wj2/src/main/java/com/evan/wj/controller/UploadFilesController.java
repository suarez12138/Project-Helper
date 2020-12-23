//package com.evan.wj.controller;
//
//import com.evan.wj.bean.All_tag_inProject;
//import com.evan.wj.dao.UploadFilesDAO;
//import com.evan.wj.result.Message_return;
//import com.evan.wj.result.TempleteResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class UploadFilesController {
//
//    @Autowired
//    UploadFilesDAO uploadFilesDAO;
//
//    @CrossOrigin
//    @GetMapping(value = "/vue-element-admin/student/personal/nnnnnnnnnnnnnnnnnnn100")
//    @ResponseBody
//    public Message_return getAllGroup(){
//
//        String ano_name = "";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式 // ：ss
//        String op_time = df.format(new Date());
//        int teacher_id = 0;
//        int pro_id = 0;
//        String text = "";
//
//
//        uploadFilesDAO.insert_submission_Assign_tea(ano_name,op_time,teacher_id,pro_id,text);
//    }
//
//
//        return new Message_return(20000,"Success!");
//    }
//
//
//
//}
