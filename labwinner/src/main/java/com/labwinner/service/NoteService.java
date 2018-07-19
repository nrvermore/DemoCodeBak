package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.Note;

public interface NoteService {
	
	public void save(Note note);
	
	public void update(Note note);
	
	public void delete(Integer id);
	
	public void deleteByReactionId(Integer id);
	
	public Note getById(Integer id);
	
	public List<Note> getByProcessId(Integer id);
	
	public List<Note> getByReactionId(Integer id);
	
	public List<Note> getAll(Integer id);
	
	public List<Integer> getByReactionIdList(Integer id);

	public List<Note> getByName(String keyword,Integer id);

}
