package com.serendipity.search.mapper;

import java.util.List;

import com.serendipity.common.pojo.SearchItem;

public interface ItemMapper {

  List<SearchItem> getItemList();

  SearchItem getItemById(long itemId);
}
