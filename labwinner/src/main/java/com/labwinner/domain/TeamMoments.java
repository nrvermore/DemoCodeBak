package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

	/**
	 * TeamMoments entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 团队圈信息表
	 */
public class TeamMoments implements java.io.Serializable {

	/**
	 * 团队圈信息主键
	 */
	private Integer teamMomentsId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 发布时间
	 */
	private Date publishTime;
	
	/**
	 * 发布正文内容
	 */
	private String publishContent;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * URL图片
	 */
	private String urlIcon;
	
	/**
	 * URL标题
	 */
	private String urlTitle;
	
	/**
	 * 项目圈点赞表
	 */
	private List<TeamMomentsImage> teamMomentsImages = new ArrayList<TeamMomentsImage>(
			0);
	
	/**
	 * 项目圈点赞表
	 */
	private List<TeamMomentsLike> teamMomentsLikes = new ArrayList<TeamMomentsLike>(
			0);
	
	/**
	 * 项目项目圈关系表
	 */
	private List<TeamProjMomentsComment> teamProjMomentsComments = new ArrayList<TeamProjMomentsComment>(
			0);

	// Constructors

	/** default constructor */
	public TeamMoments() {
	}

	/** full constructor */
	public TeamMoments(SysUser sysUser, Date publishTime,
			String publishContent, String url) {
		this.sysUser = sysUser;
		this.publishTime = publishTime;
		this.publishContent = publishContent;
		this.url = url;
	}

	public Integer getTeamMomentsId() {
		return teamMomentsId;
	}

	public void setTeamMomentsId(Integer teamMomentsId) {
		this.teamMomentsId = teamMomentsId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishContent() {
		return publishContent;
	}

	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TeamMomentsImage> getTeamMomentsImages() {
		return teamMomentsImages;
	}

	public void setTeamMomentsImages(List<TeamMomentsImage> teamMomentsImages) {
		this.teamMomentsImages = teamMomentsImages;
	}

	public List<TeamMomentsLike> getTeamMomentsLikes() {
		return teamMomentsLikes;
	}

	public void setTeamMomentsLikes(List<TeamMomentsLike> teamMomentsLikes) {
		this.teamMomentsLikes = teamMomentsLikes;
	}

	public List<TeamProjMomentsComment> getTeamProjMomentsComments() {
		return teamProjMomentsComments;
	}

	public void setTeamProjMomentsComments(
			List<TeamProjMomentsComment> teamProjMomentsComments) {
		this.teamProjMomentsComments = teamProjMomentsComments;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

}