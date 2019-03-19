package com.serendipity.search.service;

import com.serendipity.common.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String keyword,int page,int rows) throws Exception;
}
