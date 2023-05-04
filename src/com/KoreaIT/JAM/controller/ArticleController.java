package com.KoreaIT.JAM.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.JAM.Article;
import com.KoreaIT.JAM.container.Container;
import com.KoreaIT.JAM.service.ArticleService;

public class ArticleController extends Container {
	private Scanner sc;
	private ArticleService articleService;

	public ArticleController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.articleService = new ArticleService(conn);
	}
	
	public void doAction(String cmd) {
		switch (cmd) {
		case "article write" :
			doWrite();
			break;
		case "article list" :
			showList();
			break;
		case "article detail" :
			showDetail(cmd);
			break;
		case "article modify" :
			doModify(cmd);
			break;
		case "article delete" :
			doDelete(cmd);
			break;
		}
	}

	private void doWrite() {
		System.out.println("== 게시물 작성 ==");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		int id = articleService.doWrite(title, body);
		
		System.out.printf("%d번 게시글이 생성되었습니다.\n", id);
	}
	
	private void showList() {
		List<Article> articles = articleService.getArticles();

		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}

		System.out.println("== 게시물 리스트 ==");
		System.out.printf("번호	|	제목	|	날짜\n");
		for (Article article : articles) {
			System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
		}
	}
	
	private void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Map<String, Object> articleMap = articleService.selectRow(id);
		
		if (articleMap.isEmpty()) {
			System.out.println("해당하는 게시글이 존재하지 않습니다.");
			return;
		}
		
		Article article = new Article(articleMap);
		System.out.printf("== %d번 게시물 상세보기 ==\n", id);
		System.out.println("번호 : " + article.id);
		System.out.println("작성일 : " + article.regDate);
		System.out.println("최근 수정일 : " + article.updateDate);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
	}
	
	private void doModify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		int articleCount = articleService.selectRowIntValue(id);
		
		if (articleCount == 0) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}
		
		System.out.printf("== %d번 게시물 수정 ==\n", id);
		System.out.print("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.print("수정할 내용 : ");
		String body = sc.nextLine();

		articleService.dbUpdate(title, body, id);
		
		System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
	}
	
	private void doDelete(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		boolean isHaveArticle = articleService.selectRowBooleanValue(id);
		
		if (!isHaveArticle) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}
		
		articleService.dbDelete(id);
		
		System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
	}
}