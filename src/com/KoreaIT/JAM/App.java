package com.KoreaIT.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.KoreaIT.JAM.controller.ArticleController;
import com.KoreaIT.JAM.controller.MemberController;

public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			
			conn = DriverManager.getConnection(url, "root", "");
			
			ArticleController articleController = new ArticleController(sc, conn);
			MemberController memberController = new MemberController(sc, conn);
		
			while (true) {
				System.out.printf("\n명령어) ");
				String cmd = sc.nextLine().trim();
		
				if (cmd.equals("exit")) {
					break;
				}
				
				String cmdBit[] = cmd.split(" ");
				
				if (cmdBit[0].equals("article")) {
					
					if (cmdBit[1].length() <= 0) {
						System.out.println("존재하지 않는 명령어입니다.");
						continue;
					}
					articleController.doAction(cmd);
					
				} else if (cmdBit[0].equals("member")) {
					if (cmdBit[1].length() <= 0) {
						System.out.println("존재하지 않는 명령어입니다.");
						continue;
					}
					memberController.doAction(cmd);
				}
			}
			System.out.println("== 프로그램 끝 ==");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
}