package com.labwinner.service;

import java.util.Date;
import java.util.List;

import com.labwinner.domain.Device;
import com.labwinner.domain.HotSearch;
/**
 * 热门搜素Service接口
 * @Description TODO
 * @author llwangi
 * @version V1.0
 * @date 2017年9月22日 
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface HotSearchService {
	
	
	public HotSearch getByName(String name) ;
	
	
	public void save(HotSearch hotSearch) ;
    
	public void update(HotSearch hotSearch) ;
	
	
}