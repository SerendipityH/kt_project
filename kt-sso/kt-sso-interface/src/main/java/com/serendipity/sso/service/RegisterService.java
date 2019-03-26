package com.serendipity.sso.service;

import com.serendipity.common.utils.E3Result;

public interface RegisterService {
	E3Result checkData(String param, int type);
}
