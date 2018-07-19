package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ModifyCode;

public interface ModifyCodeService {
	
    public void save(ModifyCode modifyCode);
	
	public void update(ModifyCode modifyCode);
	
	public void delete(Integer id);
	
	public List<ModifyCode> getAll();
	
	public List<ModifyCode> getAllName();
	
	public List<ModifyCode> getFlagAll();
	
	public List<ModifyCode> getAllPageable(String keyword);
	
	public ModifyCode getById(Integer id);

}
