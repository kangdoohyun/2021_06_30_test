package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int articleId;
	private int like;
	private int dislike;
	
}
