package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.repository.LikeRepository;

public class LikeService {
	private LikeRepository likeRepository;

	public LikeService() {
		likeRepository = Container.getLikeRepository();
	}
	public Like like(String relTypeCode, int relId, int memberId, int like, int disLike) {
		return likeRepository.like(relTypeCode, relId, memberId, like, disLike);
	}
	public Like cancelLike(String relTypeCode, int relId, int memberId) {
		return likeRepository.cancelLike(relTypeCode, relId, memberId);
	}
	public Like dislike(String relTypeCode, int relId, int memberId, int like, int disLike) {
		return likeRepository.dislike(relTypeCode, relId, memberId, like, disLike);
	}
	public Like cancelDislike(String relTypeCode, int relId, int memberId) {
		return likeRepository.cancelDislike(relTypeCode, relId, memberId);
	}

}
