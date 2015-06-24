package com.wonders.asset.web.action;

import java.util.Map;

import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.Action;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Attachment;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.service.AttachmentService;

public class AttachmentAction extends AbstractBaseAction{
	private AttachmentService attachmentService;

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public String showAttachment() throws Exception{
		
//		String objectId = request.getParameter("objectId_equal_filter");
//		String type = request.getParameter("type_equal_filter");
		Map<String,String> filter = createFilterMap();
//		filter.put("objectId_equal_filter", objectId);
//		filter.put("type_equal_filter", type);
		Map<String,String> sort = createSortMap();
		Pagination<Attachment> attachments = attachmentService.findBy(filter, sort, 0, Integer.MAX_VALUE);
		JsonConfig jsonConfig = basicJsonCfg.copy();
		String[] excusions = new String[]{};
		jsonConfig.registerPropertyExclusions(BorrowAsset.class, excusions);
		renderJson(attachments.getResult(), attachments.getTotalCount(), jsonConfig);
		return Action.NONE;
	}
}
