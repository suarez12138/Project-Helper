package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.YQ_GroupInfo;
import com.evan.wj.bean.YQ_groupID_cnt;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.YQ_groupInfoDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class YQ_GroupInfoController {

    @Autowired
    YQ_groupInfoDAO yq_groupInfoDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/group/joinedGroup")
    @ResponseBody
    public TempleteResult<YQ_GroupInfo> get_YQ_Group_info(@RequestParam("project_id") int project_id){
        int course_id = yq_groupInfoDAO.getCourseID(project_id).get(0);
        List<YQ_groupID_cnt> Gro_cnt = yq_groupInfoDAO.getGroupID_cnt(project_id);
        List<YQ_GroupInfo> sub1 = yq_groupInfoDAO.get_mostData(project_id,course_id);

        for (YQ_GroupInfo m1:sub1) {
            for(YQ_groupID_cnt c1:Gro_cnt){
                if(m1.getGro_id() == c1.getGro_id()){
                    m1.setGroup_number(c1.getCnt());
                    break;
                }
            }
        }

        TempleteResult<YQ_GroupInfo> allProjectResult_t = new TempleteResult<YQ_GroupInfo>(20000,sub1);

        return allProjectResult_t;
    }
}


