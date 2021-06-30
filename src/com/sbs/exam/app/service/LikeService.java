package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.repository.LikeRepository;

public class LikeService {
	private LikeRepository likeRepository;

	public LikeService() {
		likeRepository = Container.getLikeRepository();
	}
	public void like(int relId, int memberId, int like, int disLike) {
		likeRepository.like(relId, memberId, like, disLike);
	}

}
