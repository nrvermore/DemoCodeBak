package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Note;

public interface NoteDao {
	
	public void save(Note note);
	
	public void update(Note note);
	
	public void delete(Integer id);
	
	public void deleteByReactionId(Integer id);
	
	public Note getById(Integer id);
	
	public List<Note> getByProcessId(Integer id);
	
	public List<Note> getByReactionId(Integer id);
	
	public List<Note> getAll(Integer id);
	
	public List<Integer> getByReactionIdList(Integer id);

	public List<Note> getByName(@Param("keyword")String keyword, @Param("id")Integer id);

}
