package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.Calendars;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MediaResource;

public class AppFirstVo {
	
	private List<MarketAssist> marketAssists;
	
	private List<MediaResource> mediaResources;
	
	private List<Calendars> calendars;
	
	public AppFirstVo(){}

	public List<MarketAssist> getMarketAssists() {
		return marketAssists;
	}

	public void setMarketAssists(List<MarketAssist> marketAssists) {
		this.marketAssists = marketAssists;
	}

	public List<MediaResource> getMediaResources() {
		return mediaResources;
	}

	public void setMediaResources(List<MediaResource> mediaResources) {
		this.mediaResources = mediaResources;
	}

	public List<Calendars> getCalendars() {
		return calendars;
	}

	public void setCalendars(List<Calendars> calendars) {
		this.calendars = calendars;
	}
	
}
