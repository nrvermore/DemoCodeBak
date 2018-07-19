package com.labwinner.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Prototype;

public interface PrototypeDao {
	
	public void save(Prototype prototype);
	
	public void update(Prototype prototype);
	
	public List<Prototype> getByProcessId(Integer id);
	
	public void delete(Integer id);
	
	public void deleteByProcessId(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public Prototype getById(Integer id);
	
	public List<Prototype> getAll();
	
	public List<Prototype> getKeyWord(String keyword);
	
	public List<String> getBarCodes();
	
	public Prototype findByBarCode(String barcode);

}
