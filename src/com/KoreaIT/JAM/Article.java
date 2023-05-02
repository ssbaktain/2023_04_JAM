package com.KoreaIT.JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String title;
	String body;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
	}
}