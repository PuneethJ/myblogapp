package com.pj.blog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CommentDto {
	
	private int id;
	private String content;

}
