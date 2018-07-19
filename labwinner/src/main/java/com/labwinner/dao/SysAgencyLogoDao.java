package com.labwinner.dao;

import com.labwinner.domain.SysAgencyLogo;

public interface SysAgencyLogoDao {
	
	public void save(SysAgencyLogo sysAgencyLogo);
	
	public SysAgencyLogo getById(Integer agencyId);
	
	public void delete(Integer id);
	
	public void update(SysAgencyLogo sysAgencyLogo);

}
