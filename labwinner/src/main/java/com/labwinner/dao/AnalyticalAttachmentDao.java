package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.AnalyticalAttachment;

public interface AnalyticalAttachmentDao {
	
	public void save(AnalyticalAttachment attachment);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<AnalyticalAttachment> getById(Integer id);
	
	public void deleteByUrl(String filename);

	public List<AnalyticalAttachment> getAllConversion();

	public void updatePdfCount(Integer anaAttachmentId);

	public void update(AnalyticalAttachment analyticalAttachment);

	public AnalyticalAttachment getByAttachmentId(Integer anaAttachmentId);

}
