package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Board;
import com.sbs.exam.app.repository.BoardRepository;

public class BoardService {
	BoardRepository boardRepository;

	public BoardService() {
		boardRepository = Container.getBoardRepository();
	}

	public Board getBoardById(int boardId) {
		return boardRepository.getBoardById(boardId);
	}

	public void makeTestData() {
		make("notice", "공지");
		make("free", "자유");		
	}

	public void make(String code, String name) {
		boardRepository.make(code, name);
	}

}
