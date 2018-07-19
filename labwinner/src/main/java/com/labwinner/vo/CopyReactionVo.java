package com.labwinner.vo;

import com.labwinner.domain.ReactionDesign;

public class CopyReactionVo {
	
	private Integer reactionId;
	
	private Integer reactionNum;
	
	private ReactionDesign reactionDesign;
	
	private Integer copynewDesign;
	
	public CopyReactionVo(){}

	public Integer getReactionId() {
		return reactionId;
	}

	public void setReactionId(Integer reactionId) {
		this.reactionId = reactionId;
	}

	public Integer getReactionNum() {
		return reactionNum;
	}

	public void setReactionNum(Integer reactionNum) {
		this.reactionNum = reactionNum;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public Integer getCopynewDesign() {
		return copynewDesign;
	}

	public void setCopynewDesign(Integer copynewDesign) {
		this.copynewDesign = copynewDesign;
	}
	
}
