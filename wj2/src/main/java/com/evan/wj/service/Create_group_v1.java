package com.evan.wj.service;

import com.evan.wj.bean.ID_week;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface Create_group_v1 {

    public TempleteResult<ID_week> get_weeks(int project_id);
    public Message_return create_group(NewGroupReceive rec);
}

