package com.serendipity.service;

import com.serendipity.common.pojo.EasyUIDataGridResult;
import com.serendipity.pojo.TbItem;

public interface ItemService {
	TbItem geTbItemById(long itemId);

	EasyUIDataGridResult getItemList(int page, int rows);
}
