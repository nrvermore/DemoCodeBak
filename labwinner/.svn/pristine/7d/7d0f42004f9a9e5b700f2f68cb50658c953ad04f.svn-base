package com.labwinner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.labwinner.domain.InformAgainst;
import com.labwinner.service.InformAgainstService;

@Service
public class InformAgainstServiceImpl implements InformAgainstService {
	
	
	@Autowired
	private com.labwinner.dao.InformAgainstDao informAgainstDao;
	
	@Override
	public void save(InformAgainst informAgainst) {
		try {
			informAgainstDao.save(informAgainst);
			
			
		} catch (Exception e) {
			
			
		}
	}

}
