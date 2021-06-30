package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.sbs.exam.app.dto.Like;
import com.sbs.exam.util.Util;

public class LikeRepository {
	private List<Like> likes;
	private int lastId;

	public LikeRepository() {
		likes = new ArrayList<>();
		lastId = 0;
	}
	public void like(int relId, int memberId, int like, int disLike) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Like like1 = new Like(id, regDate, updateDate, memberId, relId, like1, disLike);
		likes.add(like1);

		lastId = id;
	}

}
