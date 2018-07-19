package com.labwinner.vo;
import java.util.List;
import java.util.Set;

import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.domain.ReactionRecord;

public class ReactionDesignVo {
	
	private ReactionDesign reactionDesign;
	
	private List<ReactionRecord> reactionRecords;
	
	private List<ReactionDesignChemical> reactionDesignChemicals;

	private List<ReactionDesignProcess> reactionDesignProcesses;

	public ReactionDesignVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReactionDesignVo(ReactionDesign reactionDesign,
			List<ReactionRecord> reactionRecords,
			List<ReactionDesignChemical> reactionDesignChemicals,
			List<ReactionDesignProcess> reactionDesignProcesses) {
		super();
		this.reactionDesign = reactionDesign;
		this.reactionRecords = reactionRecords;
		this.reactionDesignChemicals = reactionDesignChemicals;
		this.reactionDesignProcesses = reactionDesignProcesses;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public List<ReactionRecord> getReactionRecords() {
		return reactionRecords;
	}

	public void setReactionRecords(List<ReactionRecord> reactionRecords) {
		this.reactionRecords = reactionRecords;
	}

	public List<ReactionDesignChemical> getReactionDesignChemicals() {
		return reactionDesignChemicals;
	}

	public void setReactionDesignChemicals(
			List<ReactionDesignChemical> reactionDesignChemicals) {
		this.reactionDesignChemicals = reactionDesignChemicals;
	}

	public List<ReactionDesignProcess> getReactionDesignProcesses() {
		return reactionDesignProcesses;
	}

	public void setReactionDesignProcesses(
			List<ReactionDesignProcess> reactionDesignProcesses) {
		this.reactionDesignProcesses = reactionDesignProcesses;
	}
	
	

}
