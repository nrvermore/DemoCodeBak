package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ReactionStatus;

public interface DeviceStatusService {

	public void save(ReactionStatus reactionStatus);
	
	public List<ReactionStatus> getAll();

}
