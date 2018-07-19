package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.QrInfo;

public interface QrInfoDao {
	
	public void save(QrInfo qrInfo);
	
	public QrInfo getById(Integer id);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
}
