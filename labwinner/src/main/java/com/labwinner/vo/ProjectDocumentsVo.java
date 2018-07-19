package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.service.ProjectDocumentsService;

public class ProjectDocumentsVo {
	
	private List<String> strFiles;
	
	private List<String> filenames;
	
	private ProjectBasicInfo projectBasicInfo;

	public ProjectDocumentsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectDocumentsVo(List<String> strFiles, List<String> filenames,
			ProjectBasicInfo projectBasicInfo) {
		super();
		this.strFiles = strFiles;
		this.filenames = filenames;
		this.projectBasicInfo = projectBasicInfo;
	}

	public List<String> getStrFiles() {
		return strFiles;
	}

	public void setStrFiles(List<String> strFiles) {
		this.strFiles = strFiles;
	}

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	
}
