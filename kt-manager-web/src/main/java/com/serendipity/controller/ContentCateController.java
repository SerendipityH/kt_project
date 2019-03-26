package com.serendipity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.common.pojo.EasyUITreeNode;
import com.serendipity.common.utils.E3Result;
import com.serendipity.content.service.ContentCategoryService;

/**
 * 内容分类管理
 * 
 * @author gqh
 *
 */
@Controller
public class ContentCateController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}

	/**
	 * 添加分类节点
	 */
	@RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(Long parentId, String name) {

		// 调用服务节点
		E3Result e3Result = contentCategoryService.addContentCategory(parentId, name);
		return e3Result;
	}

}
