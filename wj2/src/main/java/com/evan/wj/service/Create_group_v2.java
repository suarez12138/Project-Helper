package com.evan.wj.service;

import com.evan.wj.bean.ID_week;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.stereotype.Service;

@Service
public interface Create_group_v2 {

    public TempleteResult<ID_week> get_weeks(int project_id);
    public Message_return create_group(NewGroupReceive rec);
}
