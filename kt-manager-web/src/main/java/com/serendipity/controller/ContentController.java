package com.serendipity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.common.utils.E3Result;
import com.serendipity.content.service.ContentService;
import com.serendipity.pojo.TbContent;

@Controller
public class ContentController {

  @Autowired
  private ContentService contentService;

  @RequestMapping(value = "/content/save", method = RequestMethod.POST)
  @ResponseBody
  public E3Result addContent(TbContent content) {
    E3Result E3Result = contentService.addContent(content);

    return E3Result;
  }

}
