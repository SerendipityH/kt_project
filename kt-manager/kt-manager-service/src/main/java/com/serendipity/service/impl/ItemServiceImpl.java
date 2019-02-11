package com.serendipity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.serendipity.common.pojo.EasyUIDataGridResult;
import com.serendipity.mapper.TbItemMapper;
import com.serendipity.pojo.TbItem;
import com.serendipity.pojo.TbItemExample;
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

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
