package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Prototype;

public interface PrototypeService {

	public void save(Prototype prototype);
	
	public void update(Prototype prototype);
	
	public void delete(Integer id);
	
	public void deleteByProcessId(Integer id);
	
	public List<Prototype> getByProcessId(Integer id);
	
	public Prototype getById(Integer id);
	
	public List<Prototype> getAll();
	
	public List<Prototype> getKeyWord(String keyword);
	
	public List<String> getBarCodes();
	
	public Prototype findByBarCode(String barcode);

}
