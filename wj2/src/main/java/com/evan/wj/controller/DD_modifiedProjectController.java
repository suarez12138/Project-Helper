package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.DD_Int_String;
import com.evan.wj.bean.DD_Project_GiveBack;
import com.evan.wj.dao.DD_modifiedProjectDAO;
import com.evan.wj.receive.DD_UpdateProjectReceive;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DD_modifiedProjectController {
    @Autowired
    DD_modifiedProjectDAO dd_modifiedProjectDAO;


    private List<String> ob_tag = new ArrayList<>();

    public void registerObserver_tag(String tag){
        ob_tag.add(tag);
    }

    public void removeObserver_tag(String tag) { ob_tag.remove(tag);}


    private List<String> ob_week = new ArrayList<>();

    public void registerObserver_week(String tag){
        ob_week.add(tag);
    }

    public void removeObserver_week(String tag) { ob_week.remove(tag);}




    public int get_idOfString(String target, List<DD_Int_String> origin_list){
        for (DD_Int_String m:origin_list) {
            if(m.getThe_string().equals(target)){
                return m.getThe_id();
            }
        }
        return -1;
    }


    public void notifyObservers_tag(String tag,List<DD_Int_String> origin_list){
        int tag_id = get_idOfString(tag,origin_list);
        update_tag(tag_id);
    }
    public void update_tag(int tag_id){
        dd_modifiedProjectDAO.delete_Tag(tag_id);
        dd_modifiedProjectDAO.delete_personTag(tag_id);
    }

    public void notifyObservers_week(String week,List<DD_Int_String> origin_list){
        int checkPoint_id = get_idOfString(week,origin_list);
        update_week(checkPoint_id);
    }
    public void update_week(int checkPoint_id){
        dd_modifiedProjectDAO.delete_checkPoint(checkPoint_id);
        dd_modifiedProjectDAO.delete_gro(checkPoint_id);
    }




    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/project/get_proj_info")
    @ResponseBody
    public TempleteResult<DD_Project_GiveBack> give_projectBack(@RequestParam("project_id") int project_id){
        List<DD_Project_GiveBack> sub1 = dd_modifiedProjectDAO.getGiveBack_main(project_id);
        List<String> tags = dd_modifiedProjectDAO.getGiveBack_tag(project_id);
        List<String> preWeeks = dd_modifiedProjectDAO.getGiveBack_preWeek(project_id);

        for(String t:tags){
            sub1.get(0).getAll_tags().add(t);
        }

        for(String w:preWeeks){
            sub1.get(0).getProject_pre_week().add(w);
        }
        TempleteResult<DD_Project_GiveBack> allProjectResult_t = new TempleteResult<DD_Project_GiveBack>(20000,sub1);

        return allProjectResult_t;
    }


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/project/update_project")
    @ResponseBody
    public Message_return update_project(@RequestBody DD_UpdateProjectReceive rec){

        int project_id = rec.getProject_id();

        String group_ddl = rec.getPro_grouping_endDay().substring(0,10) + " " + rec.getPro_grouping_endHms().substring(11,16);
        dd_modifiedProjectDAO.update_project_main(rec.getAcross_lab(),rec.getForce_join(),group_ddl,rec.getMax(),rec.getMin(),rec.getProject_name(),rec.getProject_id());

        List<DD_Int_String> origin_tags = dd_modifiedProjectDAO.get_tags_withID(project_id);
        List<DD_Int_String> origin_preWeeks = dd_modifiedProjectDAO.get_preWeek_withID(project_id);
        for(DD_Int_String ot:origin_tags){
            registerObserver_tag(ot.getThe_string());
        }
        for(DD_Int_String ot:origin_preWeeks){
            registerObserver_week(ot.getThe_string());
        }
        List<String> tags = rec.getAll_tags();
        List<String> preWeeks = rec.getProject_pre_week();
        for(String t:tags){
            removeObserver_tag(t);
        }
        for(String w:preWeeks){
            removeObserver_week(w);
        }
        for(String t:ob_tag){
            notifyObservers_tag(t,origin_tags);
        }
        for(String w:ob_week){
            notifyObservers_week(w,origin_preWeeks);
        }

        for(DD_Int_String ot:origin_tags){
            tags.remove(ot.getThe_string());
        }
        for(DD_Int_String ot:origin_preWeeks){
            preWeeks.remove(ot.getThe_string());
        }

        for(String t:tags){
            dd_modifiedProjectDAO.insert_tag(project_id,t);
        }
        for(String w:preWeeks){
            dd_modifiedProjectDAO.insert_check_point(w,project_id);
        }
        return new Message_return(20000, "Success!");
    }



}
