package com.KoreaIT.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.JAM.dao.ArticleDao;
import com.KoreaIT.JAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body, int loginedMemberId) {
		return articleDao.doWrite(title, body, loginedMemberId);
	}

	public List<Article> getArticles() {
		
		List<Map<String,Object>> articleListMap = articleDao.getArticles();
		
		List<Article> articles = new ArrayList<>();
		
		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Article getArticle(int id) {
		Map<String, Object> articleMap = articleDao.getArticle(id);
		
		if (articleMap.isEmpty()) {
			return null;
		}
		return new Article(articleMap);
	}

	public int getWriterId(int id) {
		return articleDao.getWriterId(id);
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public int increaseHit(int id) {
		return articleDao.increaseHit(id);
	}
}