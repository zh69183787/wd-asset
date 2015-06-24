package com.wonders.asset.service;

import org.springframework.stereotype.Service;

/**
 * xml数据解析服务
 * @author Kai Yao
 * @date 2013-11-15
 */
@Service
public interface XmlDataResolveService {

	public String parseDataOfXml(String dataOfXmlString, String type);
	
}
