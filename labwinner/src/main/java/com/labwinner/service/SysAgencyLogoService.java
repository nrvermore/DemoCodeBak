package com.labwinner.service;

import com.labwinner.domain.SysAgencyLogo;

public interface SysAgencyLogoService {

	public void save(SysAgencyLogo sysAgencyLogo);
	
	public SysAgencyLogo getById(Integer agencyId);
	
	public void delete(Integer id);
	
	public void update(SysAgencyLogo sysAgencyLogo);
}
