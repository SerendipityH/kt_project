package com.serendipity.service;

import java.util.List;

import com.serendipity.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatlist(long parentId);
}
