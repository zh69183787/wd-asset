package com.wonders.asset.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.LineService;
import com.wonders.asset.service.StationService;
import com.wonders.framework.util.ServiceProvider;

public class StationAction extends AbstractBaseAction{


	private StationService stationService;

	public StationService getStationService() {
		return stationService;
	}

	public void setStationService(StationService stationService) {
		this.stationService = stationService;
	}

	public void findStationByLine() throws DocumentException, IOException{
		String lineId = getRequestParameter("id");
		List<Station> stations = ServiceProvider.getService(StationService.class).findByLineId(lineId);
		renderJson(stations);
	}
	
	public void checkUniqueness() throws DocumentException, IOException{
		String id =getRequestParameter("id");
		String lineId = getRequestParameter("lineId");
		String name =getRequestParameter("name");
		String code =getRequestParameter("code");
		String version =getRequestParameter("version");
		String info="";
		boolean status = ServiceProvider.getService(StationService.class).isUniquene(id, name, code, version,lineId);
		if(!status){
			info="名称或代码重复！";
		}else{
			info="success";
		}
		render("text/html", info);
	}
	
	public void saveStation() throws DocumentException{
		String id = getRequestParameter("id");
		Station station = null;
		if(StringUtils.isNotEmpty(id)){
			station = ServiceProvider.getService(StationService.class).findById(id);
		}else{
			station = new Station();
		}
		String lineId = getRequestParameter("lineId");
		String name = getRequestParameter("name");
		String code = getRequestParameter("code");
		String remark = getRequestParameter("remark");
		String version = getRequestParameter("version");
		String publish = getRequestParameter("publish");
		String codeId = ServiceProvider.getService(StationService.class).findNextCodeId(version);
		station.setName(name);
		station.setCode(code);
		station.setCodeId(codeId);
		station.setRemarks(remark);
		station.setVersion(version);
		station.setPublish(publish);
		if(StringUtils.isNotEmpty(lineId)){
			station.setLine(ServiceProvider.getService(LineService.class).findById(lineId));
		}
		ServiceProvider.getService(StationService.class).saveStation(station);
	}
	
	public void deleteStation() throws DocumentException{
		String id = getRequestParameter("id");
		ServiceProvider.getService(StationService.class).deleteById(id);
	}
	
}
