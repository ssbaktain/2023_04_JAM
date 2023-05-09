package com.KoreaIT.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.KoreaIT.JAM.session.Session;

public class Article {
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public int writerId;
	public String title;
	public String body;
	
	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.writerId = Session.loginedMember.id;
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
	}
}