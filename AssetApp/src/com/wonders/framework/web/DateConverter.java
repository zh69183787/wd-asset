package com.wonders.framework.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * 
 * 日期转换类
 * 
 * @author zhang.dingsheng
 * 
 */
public class DateConverter extends DefaultTypeConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter#convertValue(java.util.Map,
	 *      java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		try {
			if (toType.getName().equals("java.util.Date")) {
				String dateValue = ((String[]) value)[0];
				if (dateValue.trim().equals("")) {
					return null;
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				return dateFormat.parse(dateValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.convertValue(context, value, toType);
	}

}
