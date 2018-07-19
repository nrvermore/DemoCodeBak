package com.labwinner.domain;

import java.util.Date;



/**
 * @Description 圈子投诉
 * @author liuhq
 * @version V1.0
 * @date 2017年5月17日 下午12:06:11
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class InformAgainst implements java.io.Serializable {

	// Fields

	/**
	 * 主键
	 */
	private Integer informAgainstId;

	/**
	 * 圈子对象
	 */
	private ProjectMoments projectMoments;

	/**
	 * 投诉人
	 */
	private SysUser sysUser;

	/**
	 * 投诉内容
	 */
	private String contents;



	/**
	 * 创建时间
	 */
	private Date createDate;


	

	public InformAgainst() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InformAgainst(Integer informAgainstId, ProjectMoments projectMoments, SysUser sysUser,
			String contents, Date createDate) {
		super();
		this.informAgainstId = informAgainstId;
		this.projectMoments = projectMoments;
		this.sysUser = sysUser;
		this.contents = contents;
		this.createDate = createDate;
	}

	public Integer getInformAgainstId() {
		return informAgainstId;
	}

	public void setInformAgainstId(Integer informAgainstId) {
		this.informAgainstId = informAgainstId;
	}

	public ProjectMoments getProjectMoments() {
		return projectMoments;
	}

	public void setProjectMoments(ProjectMoments projectMoments) {
		this.projectMoments = projectMoments;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	
	
	
	
}