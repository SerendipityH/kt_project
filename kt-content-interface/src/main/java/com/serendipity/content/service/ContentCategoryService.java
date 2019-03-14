package com.serendipity.content.service;

import java.util.List;

import com.serendipity.common.pojo.EasyUITreeNode;
import com.serendipity.common.utils.E3Result;

public interface ContentCategoryService {
	
	List<EasyUITreeNode> getContentCatList(long parentId);
	
	E3Result addContentCategory(long parentId,String name);
}
