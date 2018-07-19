package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ReactionImage entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验图片表
	 */
public class ReactionImage implements java.io.Serializable {

	/**
	 * 试验图片主键
	 */
	private Integer reactionImageId;
	
	/**
	 * 试验图片
	 */
	private String reactionImage;
	
	private Note note;
	
	/** default constructor */
	public ReactionImage() {
	}

	public Integer getReactionImageId() {
		return reactionImageId;
	}

	public void setReactionImageId(Integer reactionImageId) {
		this.reactionImageId = reactionImageId;
	}

	public String getReactionImage() {
		return reactionImage;
	}

	public void setReactionImage(String reactionImage) {
		this.reactionImage = reactionImage;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	
	

}