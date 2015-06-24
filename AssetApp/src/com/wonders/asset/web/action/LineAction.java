package com.wonders.asset.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;
import com.wonders.asset.model.bo.LineVo;
import com.wonders.asset.service.LineService;
import com.wonders.asset.service.StationService;
import com.wonders.framework.util.ServiceProvider;

public class LineAction extends AbstractBaseAction{


	private LineService lineService;

	public LineService getLineService() {
		return lineService;
	}

	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}
	
	public void getAllLine() throws DocumentException, IOException{
		List<Line> lines = ServiceProvider.getService(LineService.class).findAllByPublish("1");
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(Line.class, new String[]{"stations","projects"});
		renderJson(lines,jsonConfig);
	}
	
	public void findLineForTree() throws IOException{
		String jsonString = lineService.findLineForTree(); 
		renderJson(jsonString);
	}
	
	public void findByVersion() throws DocumentException, IOException, IllegalAccessException, InvocationTargetException{
		String version = getRequestParameter("version");
		List<Line> lines = ServiceProvider.getService(LineService.class).findByVersion(version);
		List<LineVo> voList = new ArrayList<LineVo>();
		if(lines!=null && lines.size()>0){
			for(int i=0,len=lines.size(); i<len; i++){
				LineVo vo = new LineVo();
				Line temp = lines.get(i);
				vo.setId(temp.getId());
				vo.setCodeId(temp.getCodeId());
				vo.setCode(temp.getCode());
				vo.setName(temp.getName());
				vo.setPublish(temp.getPublish());
				vo.setRemarks(temp.getRemarks());
				vo.setShortName(temp.getShortName());
				vo.setVersion(temp.getVersion());
				vo.setStations(ServiceProvider.getService(LineService.class).sortStations(lines.get(i).getStations()));
				voList.add(vo);
			}
		}
		JsonConfig jsonConfig = basicJsonCfg.copy();
		jsonConfig.registerPropertyExclusions(LineVo.class, null);
		renderJson(voList,jsonConfig);
	}
	
	public void findAllVersion() throws DocumentException, IOException{
		List<String> versions = ServiceProvider.getService(LineService.class).showVersion();
		renderJson(versions);
	}
	
	public void checkUniqueness() throws DocumentException, IOException{
		String id =getRequestParameter("id");
		String name =getRequestParameter("name");
		String code =getRequestParameter("code");
		String version =getRequestParameter("version");
		String info = "";
		boolean status = ServiceProvider.getService(LineService.class).isUniquene(id, name, code, version);
		if(!status){
			info = "名称或代码重复！";
		}else{
			info = "success";
		}
		render("text/html",info);
	}
	
	
	public void saveLine() throws DocumentException{
		String id =getRequestParameter("id");
		Line line = null;
		if(id!=null && !"".equals(id)){
			line = ServiceProvider.getService(LineService.class).findById(id);
		}else{
			line = new Line();
		}
		String name =getRequestParameter("name");
		String shortName =getRequestParameter("shortName");
		String code =getRequestParameter("code");
		String remark =getRequestParameter("remark");
		String version =getRequestParameter("version");
		String publish =getRequestParameter("publish");
		
		String codeId = ServiceProvider.getService(LineService.class).findNextCodeId(version);
		
		line.setName(name);
		line.setShortName(shortName);
		line.setCode(code);
		line.setCodeId(codeId);
		line.setRemarks(remark);
		line.setVersion(version);
		line.setPublish(publish);
		ServiceProvider.getService(LineService.class).saveLine(line);
	}
	
	public void deleteLine() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		List<Station> stations = ServiceProvider.getService(StationService.class).findByLineId(id);
		if(stations!=null && stations.size()>0){
			renderJson("该线路下已有车站，无法删除");
			return ;
		}
		ServiceProvider.getService(LineService.class).deleteLine(id);
	}

	public void copyCurrentVersion() throws DocumentException, IllegalAccessException, InvocationTargetException, IOException{
		String version = getRequestParameter("version");
		String newVersion = (ServiceProvider.getService(LineService.class).findLasetVersion()+1)+"";
		List<Line> lines = ServiceProvider.getService(LineService.class).findByVersion(version);
		if(lines!=null && lines.size()>0){
			for(int i=0,len=lines.size(); i<len; i++){
				//复制线路
				Line tempLine = lines.get(i);
				Line saveLine = new Line();
				BeanUtils.copyProperties(saveLine, tempLine);
				saveLine.setId("");
				saveLine.setVersion(newVersion);
				saveLine.setPublish("0");
				saveLine.setProjects(null);
				saveLine.setStations(null);
				ServiceProvider.getService(LineService.class).saveLine(saveLine);	
				
				
				//复制线路下的车站
				List<Station> tempStationList = ServiceProvider.getService(StationService.class).findByLineId(tempLine.getId());
				if(tempStationList!=null && tempStationList.size()>0){
					List<Station> saveStationList = new ArrayList<Station>();
					for(int j=0,len2=tempStationList.size();j<len2;j++){
						Station tempStation = tempStationList.get(j);
						Station saveStation = new Station();
						BeanUtils.copyProperties(saveStation, tempStation);
						saveStation.setId("");
						saveStation.setVersion(newVersion);
						saveStation.setPublish("0");
						saveStation.setLine(saveLine);
						saveStationList.add(saveStation);
					}
					ServiceProvider.getService(StationService.class).saveAll(saveStationList);
				}
			}
		}
		renderJson(newVersion);
	}
	
	public void publish() throws DocumentException{
		String version = getRequestParameter("version");
		ServiceProvider.getService(LineService.class).publish(version);
		ServiceProvider.getService(StationService.class).publish(version);
		
		
	}
	
	public void findStationById() throws Exception{
		String id = getRequestParameter("lineCodeId");
		Line line = ServiceProvider.getService(LineService.class).findById(id);
		JsonConfig jsonConfig = basicJsonCfg.copy();
		String[] excusions = new String[]{"code","remarks","line","version","publish","codeId"};
		jsonConfig.registerPropertyExclusions(Station.class, excusions);
		renderJson(line.getStations(), jsonConfig);
	}
	
	
	
	
	
	
	
}
