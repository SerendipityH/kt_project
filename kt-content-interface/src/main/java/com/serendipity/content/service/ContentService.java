package com.serendipity.content.service;

import java.util.List;

import com.serendipity.common.utils.E3Result;
import com.serendipity.pojo.TbContent;

public interface ContentService {
    E3Result addContent(TbContent content);

    List<TbContent> getContentListByCid(long cid);
}
