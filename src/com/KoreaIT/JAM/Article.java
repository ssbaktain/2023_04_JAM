package com.KoreaIT.JAM;

public class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}