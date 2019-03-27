package com.serendipity.sso.service;

import com.serendipity.common.utils.E3Result;
import com.serendipity.pojo.TbUser;

public interface RegisterService {
    E3Result checkData(String param, int type);

    E3Result register(TbUser user);
}
