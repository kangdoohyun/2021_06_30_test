package com.sbs.exam.app.service;

import java.util.List;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.repository.ArticleRepository;

public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService() {
		articleRepository = Container.getArticleRepository();
	}

	public int write(int boardId, int memberId, String title, String body, int hitCount) {
		return articleRepository.write(boardId, memberId, title, body, hitCount);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public void deleteArticleById(int id) {
		articleRepository.deleteArticleById(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public void makeTestData() {
		for (int i = 0; i < 100; i++) {
			String title = "제목 " + (i + 1);
			String body = "내용 " + (i + 1);
			write(i % 2 + 1, i % 2 + 1, title, body, (int)(Math.random()*100));
		}
	}

	public List<Article> getFilteredArticles(int page, int itemsInAPage, int boardId, String searchKeyword, String searchKeywordTypeCode, String orderByColumn, String orderAscTypeCode) {
		return articleRepository.getFilteredArticles(page, itemsInAPage, boardId, searchKeyword, searchKeywordTypeCode, orderByColumn, orderAscTypeCode);
	}
}
