package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.Attachment;

public interface AttachmentDao {

	public void save(Attachment attachment);
	
	public void update(Attachment attachment);
	
	public void delete(Integer id);
	
	public Attachment getById(Integer id);
	
	public List<Attachment> getAll();
	
	public void deleteByMsgId(Integer id);

	public List<Attachment> getAllConversion();

	public void updatePdfCount(Integer attachmentId);

	public List<Attachment> getByMessageId(Integer messageId);
}
