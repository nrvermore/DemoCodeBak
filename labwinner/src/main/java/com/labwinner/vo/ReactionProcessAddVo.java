package com.labwinner.vo;

import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionProcess;

public class ReactionProcessAddVo {
	
	private ReactionProcess reactionProcess;
	
	private Reaction reaction;

	public ReactionProcessAddVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReactionProcessAddVo(ReactionProcess reactionProcess,
			Reaction reaction) {
		super();
		this.reactionProcess = reactionProcess;
		this.reaction = reaction;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	

}
