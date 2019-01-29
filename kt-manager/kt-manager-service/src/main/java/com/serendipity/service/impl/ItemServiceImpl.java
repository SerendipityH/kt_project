package com.serendipity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serendipity.mapper.TbItemMapper;
import com.serendipity.pojo.TbItem;
import com.serendipity.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
    
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem geTbItemById(long itemId) {
		// TODO Auto-generated method stub
		TbItem tbItem=itemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

}
