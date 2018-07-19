package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 市场求助
 * @author xux
 *
 */
public class MarketAssist implements java.io.Serializable{
	
	/**
	 * 市场求助主键
	 */
	private Integer marketAssistId;
	
	/**
	 * 市场求助主题
	 */
	private String title;
	
	/**
	 * 市场求助内容
	 */
	private String assistContent;
	
	/**
	 * 市场求助发起人
	 */
	private SysUser sysUser;
	
	/**
	 * 发布人签约机构
	 */
	private SysSigningAgency sysSigningAgency;
	
	/**
	 * 问题是否解决
	 */
	private String isSolve;
	
	/**
	 * 发布时间
	 */
	private Date createDate;
	
	private Integer commentNum;
	
	private Integer keywordId;
	
	/**
	 * 点赞集合
	 */
	private List<CommentLike> commentLikes = new ArrayList<CommentLike>(0);
	
	/**
	 * 图片
	 */
	private List<MarketAssistImage> marketAssistImages = new ArrayList<MarketAssistImage>(0);
	
	
	/**
	 * 信息广场语音
	 */
	private List<MarketAssistVoice> marketAssistVoices = new ArrayList<MarketAssistVoice>(0);
	
	/**
	 * 评论
	 */
	private List<AssistComment> assistComments = new ArrayList<AssistComment>(0);

	public MarketAssist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarketAssist(Integer marketAssistId, String title,
			String assistContent, SysUser sysUser,
			SysSigningAgency sysSigningAgency, String isSolve, Date createDate,
			Integer commentNum, Integer keywordId,
			List<CommentLike> commentLikes,
			List<MarketAssistImage> marketAssistImages,
			List<MarketAssistVoice> marketAssistVoices,
			List<AssistComment> assistComments) {
		super();
		this.marketAssistId = marketAssistId;
		this.title = title;
		this.assistContent = assistContent;
		this.sysUser = sysUser;
		this.sysSigningAgency = sysSigningAgency;
		this.isSolve = isSolve;
		this.createDate = createDate;
		this.commentNum = commentNum;
		this.keywordId = keywordId;
		this.commentLikes = commentLikes;
		this.marketAssistImages = marketAssistImages;
		this.marketAssistVoices = marketAssistVoices;
		this.assistComments = assistComments;
	}

	public Integer getMarketAssistId() {
		return marketAssistId;
	}

	public void setMarketAssistId(Integer marketAssistId) {
		this.marketAssistId = marketAssistId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAssistContent() {
		return assistContent;
	}

	public void setAssistContent(String assistContent) {
		this.assistContent = assistContent;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysSigningAgency getSysSigningAgency() {
		return sysSigningAgency;
	}

	public void setSysSigningAgency(SysSigningAgency sysSigningAgency) {
		this.sysSigningAgency = sysSigningAgency;
	}

	public String getIsSolve() {
		return isSolve;
	}

	public void setIsSolve(String isSolve) {
		this.isSolve = isSolve;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public List<CommentLike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLike> commentLikes) {
		this.commentLikes = commentLikes;
	}

	public List<MarketAssistImage> getMarketAssistImages() {
		return marketAssistImages;
	}

	public void setMarketAssistImages(List<MarketAssistImage> marketAssistImages) {
		this.marketAssistImages = marketAssistImages;
	}

	public List<MarketAssistVoice> getMarketAssistVoices() {
		return marketAssistVoices;
	}

	public void setMarketAssistVoices(List<MarketAssistVoice> marketAssistVoices) {
		this.marketAssistVoices = marketAssistVoices;
	}

	public List<AssistComment> getAssistComments() {
		return assistComments;
	}

	public void setAssistComments(List<AssistComment> assistComments) {
		this.assistComments = assistComments;
	}
	
	
}
