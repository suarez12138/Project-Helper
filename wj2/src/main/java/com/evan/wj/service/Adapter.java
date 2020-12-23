package com.evan.wj.service;

import com.evan.wj.bean.ID_week;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class Adapter implements Create_group_v2{

    private Create_group_v1 adaptee;
    public Adapter(Create_group_v1 adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public TempleteResult<ID_week> get_weeks(int project_id) {
        return adaptee.get_weeks(project_id);
    }

    @Override
    public Message_return create_group(NewGroupReceive rec) {
        return adaptee.create_group(rec);
    }
}
