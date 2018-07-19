package com.labwinner.controller;

import java.util.List;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.labwinner.domain.PriceCurrency;
import com.labwinner.service.PriceCurrencyService;

@RestController
public class PriceCurrencyController {
	
	private static Logger logger = LoggerFactory
			.getLogger(PriceCurrencyController.class);
	
	@Autowired 
	private PriceCurrencyService priceCurrencyService;
	
	@RequestMapping(value = "/priceCurrency/list", method = RequestMethod.GET)
	public List<PriceCurrency> getAll() {
		List<PriceCurrency> list = priceCurrencyService.findAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}


	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/priceCurrency/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody PriceCurrency priceCurrency) {
		try {
			priceCurrencyService.save(priceCurrency);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/priceCurrency/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		try {
			priceCurrencyService.delete(id);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/priceCurrency", method = RequestMethod.PUT)
	public void Update(@RequestBody PriceCurrency priceCurrency) {
		try {
			priceCurrencyService.update(priceCurrency);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/priceCurrency/{id}", method = RequestMethod.GET)
	public void getById(@PathVariable("id") Integer id) {
		try {
			priceCurrencyService.getById(id);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
