package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.NoteAssistant;

public interface NoteAssistantService {

	public void save(NoteAssistant noteAssistant);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public void update(NoteAssistant noteAssistant);
	
	public List<NoteAssistant> getById(Integer id);
}
