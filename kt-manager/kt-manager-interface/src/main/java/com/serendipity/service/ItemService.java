package com.serendipity.service;

import com.serendipity.common.pojo.EasyUIDataGridResult;
import com.serendipity.common.utils.E3Result;
import com.serendipity.pojo.TbItem;

public interface ItemService {
	TbItem geTbItemById(long itemId);

	EasyUIDataGridResult getItemList(int page, int rows);
	
	E3Result addItem(TbItem item,String desc);
}
