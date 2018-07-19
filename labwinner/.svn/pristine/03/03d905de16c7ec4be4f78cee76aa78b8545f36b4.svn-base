package com.labwinner.domain;
import java.util.ArrayList;
import java.util.List;

	/**
	 * ReactionDesignChemical entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设计溶液表
	 */
public class ReactionDesignSolution implements java.io.Serializable {

	/**
	 * 溶液设计主键
	 */
	private Integer solutionDesignId;
	
	/**
	 * 试验设计主键
	 */
	private ReactionDesign reactionDesign;
	
	/**
	 * 溶液主键
	 */
	private SolutionEntity solutionEntity;
	
	/**
	 * 是否可变
	 */
	private Boolean changeable;
	
	/**
	 * 溶液设计用量表
	 */
	private List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(0);

	public ReactionDesignSolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReactionDesignSolution(Integer solutionDesignId,
			ReactionDesign reactionDesign, SolutionEntity solutionEntity,
			Boolean changeable, List<SolutionDesignDosage> solutionDesignDosages) {
		super();
		this.solutionDesignId = solutionDesignId;
		this.reactionDesign = reactionDesign;
		this.solutionEntity = solutionEntity;
		this.changeable = changeable;
		this.solutionDesignDosages = solutionDesignDosages;
	}

	public Integer getSolutionDesignId() {
		return solutionDesignId;
	}

	public void setSolutionDesignId(Integer solutionDesignId) {
		this.solutionDesignId = solutionDesignId;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public SolutionEntity getSolutionEntity() {
		return solutionEntity;
	}

	public void setSolutionEntity(SolutionEntity solutionEntity) {
		this.solutionEntity = solutionEntity;
	}

	public Boolean getChangeable() {
		return changeable;
	}

	public void setChangeable(Boolean changeable) {
		this.changeable = changeable;
	}

	public List<SolutionDesignDosage> getSolutionDesignDosages() {
		return solutionDesignDosages;
	}

	public void setSolutionDesignDosages(
			List<SolutionDesignDosage> solutionDesignDosages) {
		this.solutionDesignDosages = solutionDesignDosages;
	}

	
}