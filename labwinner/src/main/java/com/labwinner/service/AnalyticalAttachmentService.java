package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.AnalyticalAttachment;
import com.labwinner.domain.Attachment;

public interface AnalyticalAttachmentService {

	public void save(AnalyticalAttachment attachment);
	
	public void delete(Integer id);
	
	public List<AnalyticalAttachment> getById(Integer id);
	
	public void deleteByUrl(String filename);
	
	public List<AnalyticalAttachment> getAllConversion();

	public void updatePdfCount(Integer anaAttachmentId);

	public void update(AnalyticalAttachment analyticalAttachment);

	public AnalyticalAttachment getByAttachmentId(Integer anaAttachmentId);
	
}
