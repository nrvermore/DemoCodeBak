package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.MolecularImage;

public interface MolecularImageDao {
	
	public void save(MolecularImage molecularImage);
	
	public void update(MolecularImage molecularImage);
	
	public void delete(Integer id);
	
	public MolecularImage getById(Integer id);
	
	public List<MolecularImage> getAll();
	
	public void deleteByUrl(String url);

}
