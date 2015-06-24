package com.wonders.asset.base.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public abstract class AbstractBaseAction implements ParameterAware,
		ServletRequestAware, ServletResponseAware {

	protected static final JsonConfig basicJsonCfg = new JsonConfig();

	protected static final DefaultNullValueProcessor nullValueProcessor = new DefaultNullValueProcessor();
	protected static final JsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();

	static {
		basicJsonCfg.registerDefaultValueProcessor(Integer.class,
				nullValueProcessor);
		basicJsonCfg.registerDefaultValueProcessor(Long.class,
				nullValueProcessor);

		basicJsonCfg.registerJsonValueProcessor(Date.class,
				dateJsonValueProcessor);
		basicJsonCfg.registerJsonValueProcessor(Timestamp.class,
				dateJsonValueProcessor);
		
		basicJsonCfg.setIgnoreDefaultExcludes(false);
		basicJsonCfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

	}

	protected Map<String, String[]> parameters;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected String getRequestParameter(String name) {
		String value = "";
		if (parameters == null) {
			value = request.getParameter(name);
		} else {
			String[] values = parameters.get(name);
			if (values != null && values.length > 0) {
				value = values[0];
			}
		}
		return (value == null) ? "" : value.trim();
	}

	protected String[] getRequestParameterValues(String name) {
		if (parameters != null) {
			return parameters.get(name);
		}
		return request.getParameterValues(name);
	}

	protected Map<String, String> createFilterMap() {
		Map<String, String> filterMap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			if (StringUtils.endsWith(name, "_filter")) {
				String value = getFilterValue(entry.getValue());
				if (value != null) {
					name = StringUtils.substringBefore(name, "_filter");
					filterMap.put(name, value);
				}
			}
		}
		return filterMap;
	}

	protected Map<String, Object> createObjectFilterMap() {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			if (StringUtils.endsWith(name, "_filter")) {
				String value = getFilterValue(entry.getValue());
				if (value != null) {
					name = StringUtils.substringBefore(name, "_filter");
					filterMap.put(name, value);
				}
			}
		}
		return filterMap;
	}

	protected Map<String, String> createGpsFilter()
			throws UnsupportedEncodingException {
		Map<String, String> filterMap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			if (StringUtils.endsWith(name, "_filter")) {
				String value = getFilterValue(entry.getValue());
				if (value != null) {
					name = StringUtils.substringBefore(name, "_filter");
					filterMap.put(name, URLDecoder.decode(value, "utf-8"));
				}
			}
		}
		return filterMap;
	}

//	protected Map<String, String> createSortMap() {
//		Map<String, String> sortMap = null;
//		String sort = getRequestParameter("sort");
//		String dir = getRequestParameter("dir");
//		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(dir)) {
//			sortMap = new LinkedHashMap<String, String>();
//			sortMap.put(sort, dir);
//		}
//		return sortMap;
//	}
	
	protected Map<String, String> createSortMap() {
		Map<String, String> sortMap = null;
		String sorting = getRequestParameter("jtSorting");
		if (StringUtils.isNotEmpty(sorting)) {
			String[] array = sorting.split(" ");
			String sort = array[0];
			String dir = array[1];
			sortMap = new LinkedHashMap<String, String>();
			sortMap.put(sort, dir);
		}
		return sortMap;
	}

	protected Map<String, String> createIbateSortMap() {
		Map<String, String> sortMap = null;
		String sort = getRequestParameter("sort");
		String dir = getRequestParameter("dir");
		if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(dir)) {
			sortMap = new LinkedHashMap<String, String>();
			sortMap.put("sort", sort);
			sortMap.put("dir", dir);
		}
		return sortMap;
	}

	private String getFilterValue(String[] values) {
		if (values != null && values.length == 1) {
			return StringUtils.isNotBlank(values[0]) ? values[0].trim() : null;
		}
		return null;
	}

	// / render jsonString helper method ///
	protected void renderJson(String jsonString) throws IOException {
		render("application/json", jsonString);
	}

    protected void renderJsonp(Object object,String callbackName) throws IOException {
        if (StringUtils.isNotBlank(callbackName)) {
            JSONObject jsonObject = JSONObject.fromObject(object, basicJsonCfg);
            render("application/json", callbackName+"("+jsonObject.toString()+");");
        } else
            renderJson(object);


    }

	protected void renderJson(Object object) throws IOException {
		renderJson(object, basicJsonCfg, true);
	}

	protected void renderJson(Object object, boolean isSucess)
			throws IOException {
		renderJson(object, basicJsonCfg, isSucess);
	}

	protected void renderJson(Object object, JsonConfig jsonConfig)
			throws IOException {
		renderJson(object, jsonConfig, true);
	}

	protected void renderJson(Object object, JsonConfig jsonConfig,
			boolean isSuccess) throws IOException {

		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		//jsonObject.element("success", isSuccess);
		render("application/json", jsonObject.toString());
	}

	protected void renderJson(Object[] object, JsonConfig jsonConfig)
			throws IOException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.element("success", true);
		jsonObject.element("data", JSONArray.fromObject(object, jsonConfig));

		render("application/json", jsonObject.toString());
	}

	protected void renderJson(Collection<?> c) throws IOException {
		renderJson(c, basicJsonCfg);
	}

	protected void renderJson(Collection<?> c, JsonConfig jsonConfig)
			throws IOException {

		render("application/json", JSONArray.fromObject(c, jsonConfig)
				.toString());
	}

	protected void renderJson(Collection<?> c, long totalCount)
			throws IOException {
		renderJson(c, totalCount, basicJsonCfg);
	}

	protected void renderJson(Collection<?> c, long totalCount,
			JsonConfig jsonConfig) throws IOException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.element("Result", "OK");
		jsonObject.element("Records", JSONArray.fromObject(c, jsonConfig));
		jsonObject.element("TotalRecordCount", totalCount);

		render("application/json", jsonObject.toString());
	}

	protected void render(String contentType, String content)
			throws IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType(contentType + "; charset=utf-8");
		response.getWriter().write(content);
		response.getWriter().flush();
	}

	// / json-lib utils class ///
	@SuppressWarnings("unchecked")
	protected static class DefaultNullValueProcessor implements
			DefaultValueProcessor {
		@Override
		public Object getDefaultValue(Class type) {
			return "";
		}
	}

	private static class DateJsonValueProcessor implements JsonValueProcessor {
		@Override
		public Object processObjectValue(String key, Object date,
				JsonConfig jsonConfig) {

			if (date != null) {
				return DateFormatUtils.format((Date) date,
						"yyyy-MM-dd HH:mm:ss");
			}
			return "";
		}
		@Override
		public Object processArrayValue(Object date, JsonConfig jsonConfig) {
			return "";
		}
	}

}
