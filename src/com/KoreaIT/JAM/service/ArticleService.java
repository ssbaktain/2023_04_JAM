package com.KoreaIT.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.JAM.Article;
import com.KoreaIT.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title, body);
	}

	public List<Article> getArticles() {
		
		List<Map<String,Object>> articleListMap = articleDao.getArticles();
		
		List<Article> articles = new ArrayList<>();
		
		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Map<String, Object> selectRow(int id) {
		return articleDao.selectRow(id);
	}

	public int selectRowIntValue(int id) {
		return articleDao.selectRowIntValue(id);
	}

	public void dbUpdate(String title, String body, int id) {
		articleDao.dbUpdate(title, body, id);
	}

	public boolean selectRowBooleanValue(int id) {
		return articleDao.selectRowBooleanValue(id);
	}

	public void dbDelete(int id) {
		articleDao.dbDelete(id);
	}
}