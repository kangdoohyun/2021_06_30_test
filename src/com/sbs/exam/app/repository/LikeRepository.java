package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.util.Util;

public class LikeRepository {
	private List<Like> likes;
	private int lastId;
	private ArticleRepository articleRepository;

	public LikeRepository() {
		likes = new ArrayList<>();
		lastId = 0;
		articleRepository = Container.getArticleRepository();
	}

	public Like like(String relTypeCode, int relId, int memberId, int like, int disLike) {
		Like like1 = null;
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		if (relTypeCode.equals("article")) {
			Like liked = getLikeByArticleIdAndMemberId(relId, memberId);
						
			if (liked != null) {
				if(liked.getDislike() == 1) {
					return liked;
				}
				return null;
			}

			like1 = new Like(id, regDate, updateDate, memberId, relId, like, disLike);
			likes.add(like1);
			
			Article article = articleRepository.getArticleById(relId);
			article.setLikeCount(article.getLikeCount() + 1);
			
			lastId = id;
		}
		
		return like1;
	}

	public Like cancelLike(String relTypeCode, int relId, int memberId) {
		Like like = null;

		if (relTypeCode.equals("article")) {
			like = getLikeByArticleIdAndMemberId(relId, memberId);
			if(like.getLike() == 0) {
				return null;
			}
		}

		if (like == null) {
			return null;
		}

		likes.remove(like);
		
		Article article = articleRepository.getArticleById(relId);
		article.setLikeCount(article.getLikeCount() - 1);
		
		return like;
	}

	private Like getLikeByArticleIdAndMemberId(int articleId, int memberId) {
		for (Like like : likes) {
			if (like.getArticleId() == articleId && like.getMemberId() == memberId) {
				return like;
			}
		}
		return null;
	}

	public Like dislike(String relTypeCode, int relId, int memberId, int like, int disLike) {
		Like like1 = null;
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		if (relTypeCode.equals("article")) {
			Like liked = getLikeByArticleIdAndMemberId(relId, memberId);
						
			if (liked != null) {
				if(liked.getLike() == 1) {
					return liked;
				}
				return null;
			}

			like1 = new Like(id, regDate, updateDate, memberId, relId, like, disLike);
			likes.add(like1);
			
			Article article = articleRepository.getArticleById(relId);
			article.setDislikeCount(article.getDislikeCount() + 1);
			
			lastId = id;
		}

		return like1;
	}

	public Like cancelDislike(String relTypeCode, int relId, int memberId) {
		Like like = null;

		if (relTypeCode.equals("article")) {
			like = getLikeByArticleIdAndMemberId(relId, memberId);
			if(like.getDislike() == 0) {
				return null;
			}
		}

		if (like == null) {
			return null;
		}

		likes.remove(like);
		
		Article article = articleRepository.getArticleById(relId);
		article.setDislikeCount(article.getDislikeCount() - 1);

		return like;
	}

}
