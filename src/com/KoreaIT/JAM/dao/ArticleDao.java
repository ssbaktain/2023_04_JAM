package com.KoreaIT.JAM.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.JAM.util.DBUtil;
import com.KoreaIT.JAM.util.SecSql;

public class ArticleDao {
	Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int doWrite(String title, String body, int loginedMemberId) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(", memberId = ?", loginedMemberId);
		
		return DBUtil.insert(conn, sql);
	}

	public List<Map<String, Object>> getArticles() {
		SecSql sql = new SecSql();
		sql.append("SELECT article.*, member.name as writerName");
		sql.append("FROM article");
		sql.append("INNER JOIN `member`");
		sql.append("ON article.memberId = member.id");
		sql.append("ORDER BY article.id DESC");
		
		return DBUtil.selectRows(conn, sql);
	}

	public Map<String, Object> getArticle(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT article.*, member.name as writerName");
		sql.append("FROM article");
		sql.append("INNER JOIN `member`");
		sql.append("ON article.memberId = member.id");
		sql.append("WHERE article.id = ?", id);
		
		return DBUtil.selectRow(conn, sql);
	}

	public int getWriterId(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT article.memberId");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		return DBUtil.selectRowIntValue(conn, sql);
	}

	public void doModify(int id, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);
		
		DBUtil.update(conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		DBUtil.delete(conn, sql);
	}

	public int increaseHit(int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET hit = hit + 1");
		sql.append("WHERE id = ?", id);
		
		return DBUtil.update(conn, sql);
	}
}