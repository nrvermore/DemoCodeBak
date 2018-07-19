package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.MarketAssistVoice;

public interface MarketAssistVoiceDao {
	
	public void save(MarketAssistVoice marketAssistVoice);
	
	public void delete(Integer id);
	
	public void update(MarketAssistVoice marketAssistVoice);
	
	public MarketAssistVoice getbyId(Integer id);
	
	public List<MarketAssistVoice> getAll();

}
