package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article implements Comparable<Article> {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	private int hitCount;
	
	@Override
	public int compareTo(Article article) {
		if (this.hitCount < article.getHitCount()) {
            return -1;
        } else if (this.hitCount > article.getHitCount()) {
            return 1;
        }
        return 0;
	}
}
