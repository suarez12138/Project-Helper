package com.evan.wj.service;

import com.evan.wj.dao.GroupDAO;
import com.evan.wj.result.MyGroupResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface GroupService {

    public MyGroupResult getMyGroup( String token, int project_id);
}
