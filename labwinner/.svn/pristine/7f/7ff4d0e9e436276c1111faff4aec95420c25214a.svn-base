package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PriceCurrencyDao;
import com.labwinner.domain.PriceCurrency;
import com.labwinner.service.PriceCurrencyService;

@Service
public class PriceCurrencyServiceImpl implements PriceCurrencyService{
	private static final Logger log = LoggerFactory
			.getLogger(PriceCurrencyServiceImpl.class);
	@Autowired 
	private PriceCurrencyDao priceCurrencyDao;
	
	public void save(PriceCurrency priceCurrency) {
		log.debug("saving Measurement instance");
		try {
			priceCurrencyDao.save(priceCurrency);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<PriceCurrency> findAll() {
		log.debug("finding all Measurement instances");
		try {
			List<PriceCurrency> priceCurrencyList= priceCurrencyDao.getAll();
			return priceCurrencyList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		priceCurrencyDao.delete(id);
	}

	@Override
	public PriceCurrency getById(Integer id) {
		// TODO Auto-generated method stub
		return priceCurrencyDao.getById(id);
	}

	@Override
	public void update(PriceCurrency priceCurrency) {
		priceCurrencyDao.update(priceCurrency);
		
	}

}