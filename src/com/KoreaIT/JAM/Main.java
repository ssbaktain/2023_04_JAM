package com.KoreaIT.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>();
		int lastArticleId = 0;
		
		System.out.println("== 프로그램 시작 ==");
		
		while (true) {
			System.out.printf("\n명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				System.out.println("== 프로그램 끝 ==");
				break;
			}
			
			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				int id = ++lastArticleId;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				articles.add(new Article(id, title, body));
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if (cmd.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				System.out.printf("번호	|	제목\n");
				for (Article article : articles) {
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
			}
		}
		sc.close();
	}
	
}