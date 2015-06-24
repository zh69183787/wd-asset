
package com.wonders.asset.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.Action;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.dao.DwAssetProjectLineValueDao;
import com.wonders.asset.model.AssetTask;
import com.wonders.asset.model.Attach;
import com.wonders.asset.service.AssetService;
import com.wonders.asset.service.AssetTaskService;
import com.wonders.framework.util.PropertiesUtil;
import com.wonders.framework.util.ServiceProvider;


public class AssetTaskAction extends AbstractBaseAction {

    private DwAssetProjectLineValueDao projectLineValueDao;

    public DwAssetProjectLineValueDao getProjectLineValueDao() {
        return projectLineValueDao;
    }

    public void setProjectLineValueDao(DwAssetProjectLineValueDao projectLineValueDao) {
        this.projectLineValueDao = projectLineValueDao;
    }

    private AssetTask assetTask = new AssetTask();
	private AssetTaskService assetTaskService;
	private AssetService assetService;

	
	private File uploadify;
	private String uploadifyFileName;
	
	private final SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	private final int size = 20;

	
	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public AssetTask getAssetTask() {
		return assetTask;
	}

	public void setAssetTask(AssetTask assetTask) {
		this.assetTask = assetTask;
	}

	public void setAssetTaskService(AssetTaskService assetTaskService) {
		this.assetTaskService = assetTaskService;
	}

	public AssetTaskService getAssetTaskService() {
		return assetTaskService;
	}
	
	public AssetService getAssetService() {
		return assetService;
	}

	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	/**
	 * 显示list页面,普通权限，无法新增任务
	 * 
	 * @throws Exception
	 */
	public void inquery() throws Exception {

		request.setAttribute("today", sdfToDate.format(new Date()));

		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}

		
		Map<String,String> mapFilter = createFilterMap();
		
		String start1 = getRequestParameter("start1");
		String start2 = getRequestParameter("start2");
		
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		Map<String,String> sort = createSortMap();
		Pagination<AssetTask> page = ServiceProvider.getService(AssetTaskService.class).findBy(mapFilter, sort, startIndex, pageSize);
		
		JsonConfig jsonConfig = basicJsonCfg.copy();
		renderJson(page.getResult(), page.getTotalCount(), jsonConfig);
	}

	
	/**
	 * 显示view页面
	 * 
	 * @throws Exception
	 */
	public String showView() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		assetTask = ServiceProvider.getService(AssetTaskService.class).findById(id);
		
		List<Attach> attachList = ServiceProvider.getService(AssetTaskService.class).findAttachByGroupId(id);

		request.setAttribute("assetTask", assetTask);
		request.setAttribute("attachList", attachList);
		
		

		return "success";
	}

	

	// 根据AssetTask.taskmemoFilter(String类型)字段的值，得到分页查询AssetInfo的条件filter
	public Map<String, Object> getAssetInfoFilterByString(String filterCondition)
			throws Exception {
		Map<String, Object> filter = new HashMap<String, Object>();
		if (filterCondition == null || "".equals(filterCondition)) {
			return null;
		} else {
			String[] conditions = filterCondition.split("；");
			if (conditions != null && conditions.length >= 1) {
				for (int i = 0; i < conditions.length; i++) {
					String[] keyAndValue = conditions[i].split("：");
					filter.put(keyAndValue[0], keyAndValue[1]);
				}
			}
			return filter;
		}
	}
	
	public Map<String, String> getAssetFilterByString(String filterCondition)
			throws Exception {
		Map<String, String> filter = new HashMap<String, String>();
		if (filterCondition == null || "".equals(filterCondition)) {
			return null;
		} else {
			String[] conditions = filterCondition.split("；");
			if (conditions != null && conditions.length >= 1) {
				for (int i = 0; i < conditions.length; i++) {
					String[] keyAndValue = conditions[i].split("：");
					String key = keyAndValue[0]+"_equals";
					String value = keyAndValue[1];
					filter.put(key,value);
				}
			}
			return filter;
		}
	}
	
	public void uploadAttach() throws DocumentException{
		System.out.println("upload start*************");
		String id = getRequestParameter("id");
		if(id.isEmpty())return ;
		
		Properties p = PropertiesUtil.loadProperties("config.properties");
		String saveDir = p.getProperty("attachPath"); 
		
		File savePath = new File(saveDir);
		if(!savePath.exists()){
			savePath.mkdirs();
		}
		
		String saveFileName = saveDir+File.separator+sdf.format(new Date())+"-"+(new Random().nextInt(10000))+".dat";
		File newFile = new File(saveFileName);
		uploadify.renameTo(newFile);		//将文件移到制定位置并且改名
		
		String fileName = uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf("."));
		String fileExt = uploadifyFileName.substring(uploadifyFileName.lastIndexOf(".")+1,uploadifyFileName.length());
		Attach attach = new Attach();
		attach.setFilename(fileName);
		attach.setFileextname(fileExt);
		attach.setFilesize(newFile.length());
		attach.setPath(saveDir);
		//attach.setUploader("");		//上传人
		attach.setUploaddate(sdfToDate.format(new Date()));
		attach.setRemoved(0l);
		//attach.setOperator("");			//操作人
		attach.setSavefilename(saveFileName);
		//attach.setOperateTime(sdf.format(new Date()));		//数据库中设计的字段是number类型的
		attach.setAppname("AssetWeb");
		//attach.setUploaderLoginName("");		//登陆的人
//		attach.setStatus("upload");
		
		attach.setGroupid(id);
		
		ServiceProvider.getService(AssetTaskService.class).saveAttach(attach);
		System.out.println("upload end*************");
		
	}
	
	public void downloadAttach() throws DocumentException{
		String id = getRequestParameter("id");
		if(id.isEmpty()) return ;
		Attach attach = ServiceProvider.getService(AssetTaskService.class).findAttachById(Long.valueOf(id));
		if(attach==null) return ;
		
		String filename = attach.getFilename()+"."+attach.getFileextname();
		InputStream is = null;
		try {
			is = new FileInputStream(attach.getSavefilename());

			int len = 0;
			byte[] buffers = new byte[1024];
			response.setCharacterEncoding("utf-8");
			response.reset();
			response.setContentType("application/x-msdownload");

			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
			OutputStream os = response.getOutputStream();
			// 把文件内容通过输出流打印到页面上供下载
			while ((len = is.read(buffers)) != -1) {
				os.write(buffers, 0, len);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAttach() throws NumberFormatException, DocumentException{
		String id = getRequestParameter("id");
		ServiceProvider.getService(AssetTaskService.class).deleteAttachById(Long.valueOf(id));
	}
	
	

	// 得到百分比数字，小数点保留2位
	public String getPercentage(int allCount, int finishCount) {
		if (finishCount != 0) {
			DecimalFormat df = new DecimalFormat("#.00");
			double result = Double.valueOf(finishCount)
					/ Double.valueOf(allCount) * 100.0d;
			if (result >= 100)
				return "100";
			String returnRusult = df.format(result);
			if (returnRusult.endsWith(".00"))
				return returnRusult.substring(0, returnRusult.indexOf("."));
			return returnRusult;
		}
		return "0";
	}

    /**
     * 获得盘点任务进展
     */
    public void getCompleterate() throws IOException {
        float completeReateNum=assetTaskService.getAssetTaskCompleteRate();
        float seetProjectRate=projectLineValueDao.getDwAssetProjectLineValueRate();
        Map<String, Float> map=new HashMap<String, Float>();
        map.put("taskRate",completeReateNum);
        map.put("projectRate",seetProjectRate);
        renderJson(map);

    }
    
    public String getCheckInfoAndAsset() throws IOException, DocumentException{
		String id = request.getParameter("id");
		int startIndex = NumberUtils.toInt(getRequestParameter("jtStartIndex"), 0);
		int pageSize = NumberUtils.toInt(getRequestParameter("jtPageSize"), 10);
		List<Object[]> list = ServiceProvider.getService(AssetTaskService.class).findCheckInfoAndAssetByTaskId(id, startIndex, pageSize);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(Object[] obj : list){
			Map map = new HashMap();
			map.put("assetNo", obj[0]);
			map.put("assetName", obj[1]);
			map.put("line", obj[2]);
			map.put("station", obj[3]);
			map.put("beginUseDate", obj[4]);
			map.put("checkInfo", obj[5]);
			map.put("checkDate", obj[6]);
			map.put("checkPerson", obj[7]);
			result.add(map);
		}
		renderJson(result, list.size());
		return Action.NONE;
	}
	
}
  
	