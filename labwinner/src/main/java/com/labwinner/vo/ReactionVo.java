package com.labwinner.vo;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;

public class ReactionVo {
	
	private Reaction reaction;
	
	private ReactionDesign reactionDesign;
	
	private List<Map<String,Object>> knowledges;
	
	public ReactionVo(){}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public List<Map<String, Object>> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Map<String, Object>> knowledges) {
		this.knowledges = knowledges;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

}
