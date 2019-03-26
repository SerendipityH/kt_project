package com.serendipity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.common.utils.E3Result;
import com.serendipity.search.service.SearchItemService;

/**
 * 导入商品数据到索引库
 * 
 * @author gqh
 *
 */
@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result e3Result = searchItemService.importAllItems();
		return e3Result;
	}
}
