package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Like;
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
		
		Like like = likeService.cancelDislike(relTypeCode, relId, memberId);
		
		if(like == null) {
			System.out.println("싫어요가 없습니다.");
			return;
		}
		
		System.out.println("싫어요 취소");
	}

	private void actionDislike(Rq rq) {
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
		
		int like = 0;
		int disLike = 1;
		
		Like like1 = likeService.dislike(relTypeCode, relId, memberId, like, disLike);
		
		if(like1 == null) {
			System.out.println("이미 싫어요를 했습니다");
			return;
		}
		if(like1.getLike() == 1) {
			System.out.println("좋아요를 한 상태로 싫어요를 할 수 없습니다");
			return;
		}
		System.out.println("싫어요");
	}

	private void actionCancelLike(Rq rq) {
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
		
		Like like = likeService.cancelLike(relTypeCode, relId, memberId);
		
		if(like == null) {
			System.out.println("좋아요가 없습니다.");
			return;
		}
		
		System.out.println("좋아요 취소");
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
		
		Like like1 = likeService.like(relTypeCode, relId, memberId, like, disLike);
		
		if(like1 == null) {
			System.out.println("이미 좋아요를 했습니다");
			return;
		}
		if(like1.getDislike() == 1) {
			System.out.println("싫어요를 한 상태로 좋아요를 할 수 없습니다");
			return;
		}
		System.out.println("좋아요");
	}

}
