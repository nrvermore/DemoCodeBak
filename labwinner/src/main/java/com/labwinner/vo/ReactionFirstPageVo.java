package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;

public class ReactionFirstPageVo {
	
	//首页我的实验
	private List<ReactionDesign> myDesignlist;
	
	//首页团队实验
	private List<ReactionDesign> teamReactionlist;
	
	//首页我的报告
	private List<Reaction> myReprotlist;
	
	//首页团队报告
	private List<Reaction> teamReprotlist;
	
	public ReactionFirstPageVo(){}

	public List<ReactionDesign> getMyDesignlist() {
		return myDesignlist;
	}

	public void setMyDesignlist(List<ReactionDesign> myDesignlist) {
		this.myDesignlist = myDesignlist;
	}

	public List<ReactionDesign> getTeamReactionlist() {
		return teamReactionlist;
	}

	public void setTeamReactionlist(List<ReactionDesign> teamReactionlist) {
		this.teamReactionlist = teamReactionlist;
	}

	public List<Reaction> getMyReprotlist() {
		return myReprotlist;
	}

	public void setMyReprotlist(List<Reaction> myReprotlist) {
		this.myReprotlist = myReprotlist;
	}

	public List<Reaction> getTeamReprotlist() {
		return teamReprotlist;
	}

	public void setTeamReprotlist(List<Reaction> teamReprotlist) {
		this.teamReprotlist = teamReprotlist;
	}

}
