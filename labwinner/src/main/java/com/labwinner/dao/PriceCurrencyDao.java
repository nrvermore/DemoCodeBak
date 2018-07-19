package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.MaterialType;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.PriceCurrency;


public interface PriceCurrencyDao {
	
	public void save(PriceCurrency priceCurrency);
	
	public List<PriceCurrency> getAll();

	public void delete(Integer id);
    
	 
	public PriceCurrency getById(Integer id);
   
	
	public void update(PriceCurrency priceCurrency); 
}
