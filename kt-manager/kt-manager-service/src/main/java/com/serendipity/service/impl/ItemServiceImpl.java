package com.serendipity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.serendipity.common.pojo.EasyUIDataGridResult;
import com.serendipity.common.utils.E3Result;
import com.serendipity.common.utils.IDUtils;
import com.serendipity.mapper.TbItemDescMapper;
import com.serendipity.mapper.TbItemMapper;
import com.serendipity.pojo.TbItem;
import com.serendipity.pojo.TbItemDesc;
import com.serendipity.pojo.TbItemExample;
import com.serendipity.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
    
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
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

	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		//补全item的属性
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo对象
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回成功
		return E3Result.ok();
	}

}
