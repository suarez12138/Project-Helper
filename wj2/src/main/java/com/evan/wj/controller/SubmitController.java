package com.evan.wj.controller;


import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.SubmitDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.ReleaseAnnouncementReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SubmitController {
        @Autowired
        SubmitDAO submitDAO;

    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

        @CrossOrigin
        @GetMapping(value = "/vue-element-admin/student/personal/tempqwfqwfqwfqwf")
        @ResponseBody
        public Message_return insert_teacher_submit_release(@RequestBody ReleaseAnnouncementReceive rec){
            int person_id = upPersonInfoDAO.getID(rec.getToken()).get(0).getId();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式 // ：ss
            String op_time = df.format(new Date());
            submitDAO.teacher_submit_insert(rec.getAnn_name(),op_time, person_id,rec.getProject_id(),rec.getText());
            return new Message_return(20000,"Success!");
        }

}
