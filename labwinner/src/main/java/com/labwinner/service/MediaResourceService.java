package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.MediaResource;

public interface MediaResourceService {
	
	public void save(MediaResource mediaResource);
	
	public void update(MediaResource mediaResource);
	
	public void delete(Integer id);
	
	public MediaResource getById(Integer id);
	
	public List<MediaResource> getByName(String name);
	
	public List<MediaResource> getAll();
	
	public List<MediaResource> getAllUsers();

	public List<MediaResource> getAllByDay();
	
	public List<MediaResource> getPageList(Integer pageCount);

	public List<MediaResource> getFirst();

}
