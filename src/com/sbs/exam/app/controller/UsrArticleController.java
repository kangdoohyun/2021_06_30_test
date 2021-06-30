package com.sbs.exam.app.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Board;
import com.sbs.exam.app.dto.Member;
import com.sbs.exam.app.service.ArticleService;
import com.sbs.exam.app.service.BoardService;
import com.sbs.exam.util.Util;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	private Scanner sc;
	private BoardService boardService;

	public UsrArticleController() {
		boardService = Container.getBoardService();
		articleService = Container.getArticleService();
		sc = Container.getSc();

		// 테스트 게시물 만들기
		makeTestData();
	}

	private void makeTestData() {
		boardService.makeTestData();
		articleService.makeTestData();
	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/article/write")) {
			actionWrite(rq);
		} else if (rq.getActionPath().equals("/usr/article/list")) {
			actionList(rq);
		} else if (rq.getActionPath().equals("/usr/article/detail")) {
			actionDetail(rq);
		} else if (rq.getActionPath().equals("/usr/article/delete")) {
			actionDelete(rq);
		} else if (rq.getActionPath().equals("/usr/article/modify")) {
			actionModify(rq);
		}
	}

	private void actionModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("새 제목 : ");
		article.setTitle(sc.nextLine().trim());
		System.out.printf("새 내용 : ");
		article.setBody(sc.nextLine().trim());
		article.setUpdateDate(Util.getNowDateStr());

		System.out.printf("%d번 게시물을 수정하였습니다.\n", id);
	}

	private void actionDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		articleService.deleteArticleById(article.getId());

		System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
	}

	private void actionDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		Member member = Container.getMemberService().getMemberById(article.getMemberId());
		
		System.out.printf("번호 : %d\n", article.getId());
		System.out.printf("작성날짜 : %s\n", article.getRegDate());
		System.out.printf("수정날짜 : %s\n", article.getUpdateDate());
		System.out.printf("작성자 : %s\n", member.getNickname());
		System.out.printf("제목 : %s\n", article.getTitle());
		System.out.printf("내용 : %s\n", article.getBody());
		System.out.printf("조회수 : %s\n", article.getHitCount());
		
		article.setHitCount(article.getHitCount() + 1);
	}

	private void actionList(Rq rq) {
		int page = rq.getIntParam("page", 1);
		int boardId = rq.getIntParam("boardId", 0);
		String searchKeyword = rq.getStrParam("searchKeyword", "");
		String searchKeywordTypeCode = rq.getStrParam("searchKeywordTypeCode", "title");
		String orderByColumn = rq.getStrParam("orderByColumn", "id");
		String orderAscTypeCode = rq.getStrParam("orderAscTypeCode", "desc");
		
		int itemsInAPage = 10;
		
		List<Article> articles = articleService.getArticles();
		List<Article> filteredArticles = articleService.getFilteredArticles(page, itemsInAPage, boardId, searchKeyword, searchKeywordTypeCode, orderByColumn, orderAscTypeCode);
		
		System.out.printf("번호 / 작성날자 / 제목 / 작성자 / 게시판 이름 / 조회수\n");
		
		if(searchKeyword.length() == 0) {
			System.out.println("전체 게시물 갯수 :" + articles.size());
		}
		else {
			System.out.println("검색된 게시물 갯수 :" + filteredArticles.size());
		}
		
		for (Article article : filteredArticles) {
			Member member = Container.getMemberService().getMemberById(article.getMemberId());
			Board board = Container.getBoardService().getBoardById(article.getBoardId());
			System.out.printf("%d / %s / %s / %s / %s / %d\n", article.getId(), article.getRegDate(), article.getTitle(), member.getNickname(), board.getName(), article.getHitCount());
		}
	}

	private void actionWrite(Rq rq) {
		int boardId = rq.getIntParam("boardId", 0);
		
		if(boardId == 0) {
			System.out.println("게시판 번호를 입력해주세요");
			return;
		}
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			System.out.println("존재하지 않는 게시판입니다.");
			return;
		}
		
		int memberId = rq.getLoginedMemberId();
		int hitCount = 0;
		
		System.out.println("== " + board.getName() + " 게시판 글 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("내용 : ");
		String body = sc.nextLine().trim();

		int id = articleService.write(boardId, memberId, title, body, hitCount);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

}
