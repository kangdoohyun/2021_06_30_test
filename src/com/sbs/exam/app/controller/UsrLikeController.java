package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.service.LikeService;

public class UsrLikeController extends Controller {
	private LikeService likeService;
	private Scanner sc;

	public UsrLikeController() {
		likeService = Container.getLikeService();
		sc = Container.getSc();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/like/like")) {
			actionLike(rq);
		} else if (rq.getActionPath().equals("/usr/like/cancelLike")) {
			actionCancelLike(rq);
		} else if (rq.getActionPath().equals("/usr/like/dislike")) {
			actionDislike(rq);
		} else if (rq.getActionPath().equals("/usr/like/cancelDislike")) {
			actionCancelDislike(rq);
		}
	}

	private void actionCancelDislike(Rq rq) {
		// TODO Auto-generated method stub
		
	}

	private void actionDislike(Rq rq) {
		// TODO Auto-generated method stub
		
	}

	private void actionCancelLike(Rq rq) {
		// TODO Auto-generated method stub
		
	}

	private void actionLike(Rq rq) {
		int relId = rq.getIntParam("id", 0);
		String relTypeCode = rq.getStrParam("relTypeCode", "article");
		int memberId = rq.getLoginedMemberId();
		
		if(relTypeCode.length() == 0) {
			System.out.println("relTypeCode를 입력해주세요");
			return;
		}
		if(relId == 0) {
			System.out.println("id를 입력해 주세요");
			return;
		}
		
		int like = 1;
		int disLike = 0;
		
		likeService.like(relId, memberId, like, disLike);
	}

}
