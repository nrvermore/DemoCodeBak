package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.IndustryReactionTemplate;
import com.labwinner.domain.ProfessionProcess;

public class IndustryReactionTemplateVo {
	
	private IndustryReactionTemplate industryReactionTemplate;

	private List<ProfessionProcess> professionProcesses;

	public IndustryReactionTemplateVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndustryReactionTemplateVo(
			IndustryReactionTemplate industryReactionTemplate,
			List<ProfessionProcess> professionProcesses) {
		super();
		this.industryReactionTemplate = industryReactionTemplate;
		this.professionProcesses = professionProcesses;
	}

	public IndustryReactionTemplate getIndustryReactionTemplate() {
		return industryReactionTemplate;
	}

	public void setIndustryReactionTemplate(
			IndustryReactionTemplate industryReactionTemplate) {
		this.industryReactionTemplate = industryReactionTemplate;
	}

	public List<ProfessionProcess> getProfessionProcesses() {
		return professionProcesses;
	}

	public void setProfessionProcesses(List<ProfessionProcess> professionProcesses) {
		this.professionProcesses = professionProcesses;
	}
	
	
	
	
}
