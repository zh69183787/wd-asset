package com.wonders.asset.service.impl;

import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.StationDao;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.StationService;

public class StationServiceImpl extends BaseServiceImpl<Station, String> implements StationService{

	private StationDao stationDao;

	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
		setBaseDao(stationDao);
	}

	@Override
	public List<Station> findByLineId(String lineId) {
		return stationDao.findByLineId(lineId);
	}

	@Override
	public boolean isUniquene(String id,String name,String code,String version,String lineId) {
		List<Line> lines = stationDao.findByCondition(id, name, code, version,lineId);
		if(lines==null || lines.size()<1) return true;
		return false;
	}

	@Override
	public Station findById(String id) {
		return stationDao.findById(id);
	}

	@Override
	public void saveStation(Station station) {
		stationDao.saveStation(station);
	}

	@Override
	public void deleteById(String id) {
		stationDao.deleteById(id);
	}

	@Override
	public void saveAll(List<Station> list) {
		stationDao.saveAll(list);
	}

	@Override
	public void publish(String version) {
		stationDao.publish(version);
	}

	@Override
	public boolean isUniqueneByVersionAndCodeId(String id, String version,
			String codeId) {
		List<Station> list = this.stationDao.findByVersionAndCodeId(id,version,codeId);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = this.stationDao.findMaxCodeId(version);
		String result = null;
		if(StringUtils.isNotEmpty(codeId)){
			try {
				result = (Integer.valueOf(codeId)+1)+"";
				return result;
			} catch (NumberFormatException e) {
				return result;
			}
		}
		return result;
	}


    public Station findByCodeAndLineId(String stationCode, String lineId) {
        return stationDao.findByCodeAndLineId( stationCode, lineId);
    }

}
