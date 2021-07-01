package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sbs.exam.app.dto.Article;
import com.sbs.exam.util.Util;

public class ArticleRepository {
	private List<Article> articles;
	private int lastId;

	public ArticleRepository() {
		articles = new ArrayList<>();
		lastId = 0;
	}

	public int write(int boardId, int memberId, String title, String body, int hitCount, int likeCount,
			int dislikeCount, List<String> keywordStr) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Article article = new Article(id, regDate, updateDate, boardId, memberId, title, body, hitCount, likeCount,
				dislikeCount, keywordStr);
		articles.add(article);

		lastId = id;

		return id;
	}

	public void deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article != null) {
			articles.remove(article);
		}
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public List<Article> getFilteredArticles(int page, int itemsInAPage, int boardId, String searchKeyword,
			String searchKeywordTypeCode, String orderByColumn, String orderAscTypeCode) {
		List<Article> filteredArticles0 = getOrderByColumnFilteredArticles(articles, orderByColumn, orderAscTypeCode);
		List<Article> filteredArticles1 = getBoardIdFilteredArticles(filteredArticles0, boardId);
		List<Article> filteredArticles2 = getSearchKeywordFilteredArticles(filteredArticles1, searchKeyword,
				searchKeywordTypeCode);
		List<Article> filteredArticles3 = getPageFilteredArticles(filteredArticles2, page, itemsInAPage);

		return filteredArticles3;
	}

	private List<Article> getOrderByColumnFilteredArticles(List<Article> articles, String orderByColumn,
			String orderAscTypeCode) {
		if (orderByColumn.equals("hitCount")) {
			if (orderAscTypeCode.equals("asc")) {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int item0 = article0.getHitCount();
						int item1 = article1.getHitCount();
						if (item0 == item1)
							return 0;
						else if (item1 > item0)
							return 1;
						else
							return -1;
					}
				});
			} else {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int hit0 = article0.getHitCount();
						int hit1 = article1.getHitCount();
						if (hit0 == hit1)
							return 0;
						else if (hit1 < hit0)
							return 1;
						else
							return -1;
					}
				});
			}
		}
		if (orderByColumn.equals("likeCount")) {
			if (orderAscTypeCode.equals("asc")) {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int item0 = article0.getLikeCount();
						int item1 = article1.getLikeCount();
						if (item0 == item1)
							return 0;
						else if (item1 > item0)
							return 1;
						else
							return -1;
					}
				});
			} else {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int hit0 = article0.getLikeCount();
						int hit1 = article1.getLikeCount();
						if (hit0 == hit1)
							return 0;
						else if (hit1 < hit0)
							return 1;
						else
							return -1;
					}
				});
			}
		}
		if (orderByColumn.equals("dislikeCount")) {
			if (orderAscTypeCode.equals("asc")) {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int item0 = article0.getDislikeCount();
						int item1 = article1.getDislikeCount();
						if (item0 == item1)
							return 0;
						else if (item1 > item0)
							return 1;
						else
							return -1;
					}
				});
			} else {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int hit0 = article0.getDislikeCount();
						int hit1 = article1.getDislikeCount();
						if (hit0 == hit1)
							return 0;
						else if (hit1 < hit0)
							return 1;
						else
							return -1;
					}
				});
			}
		}
		if (orderByColumn.equals("id")) {
			if (orderAscTypeCode.equals("asc")) {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int item0 = article0.getId();
						int item1 = article1.getId();
						if (item0 == item1)
							return 0;
						else if (item1 > item0)
							return 1;
						else
							return -1;
					}
				});
			} else {
				articles.sort(new Comparator<Article>() {
					@Override
					public int compare(Article article0, Article article1) {
						int hit0 = article0.getId();
						int hit1 = article1.getId();
						if (hit0 == hit1)
							return 0;
						else if (hit1 < hit0)
							return 1;
						else
							return -1;
					}
				});
			}
		}

		return articles;
	}

	private List<Article> getSearchKeywordFilteredArticles(List<Article> articles, String searchKeyword,
			String searchKeywordTypeCode) {
		if (searchKeyword.length() == 0) {
			return articles;
		}

		List<Article> filteredArticles = new ArrayList<>();

		if (searchKeywordTypeCode.equals("title")) {
			for (Article article : articles) {
				if (article.getTitle().contains(searchKeyword)) {
					filteredArticles.add(article);
				}
			}
		} else if (searchKeywordTypeCode.equals("body")) {
			for (Article article : articles) {
				if (article.getBody().contains(searchKeyword)) {
					filteredArticles.add(article);
				}
			}
		} else if (searchKeywordTypeCode.equals("keyword")) {
			for (Article article : articles) {
				if (article.getKeywordStr().contains(searchKeyword)) {
					filteredArticles.add(article);
				}
			}
		}

		return filteredArticles;
	}

	private List<Article> getPageFilteredArticles(List<Article> articles, int page, int itemsInAPage) {
		List<Article> filteredArticles = new ArrayList<>();

		int jumpArticlesIndex = (page - 1) * itemsInAPage;
		int startIndex = articles.size() - jumpArticlesIndex - 1;
		int endIndex = startIndex - itemsInAPage + 1;
		if (endIndex < 0) {
			endIndex = 0;
		}

		for (int start = startIndex; start >= endIndex; start--) {
			filteredArticles.add(articles.get(start));
		}

		return filteredArticles;
	}

	private List<Article> getBoardIdFilteredArticles(List<Article> articles2, int boardId) {
		if (boardId == 0) {
			return articles;
		}
		List<Article> filteredArticles = new ArrayList<>();

		for (Article article : articles) {
			if (article.getBoardId() == boardId) {
				filteredArticles.add(article);
			}
		}

		return filteredArticles;
	}

}
