package com.wonders.asset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.LineDao;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.LineService;
import com.wonders.framework.util.SortUtils;

public class LineServiceImpl extends BaseServiceImpl<Line, String> implements LineService{

	private LineDao lineDao;

	public LineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
		setBaseDao(lineDao);
	}


    @Override
    public Pagination findAllLine(int page, int pageSize) {
        return lineDao.findAllTheLine(page,pageSize);
    }

    @Override
	public List<Line> findAll() {
		return lineDao.findAllByOrder();
	}

	
	
	@Override
	public List<Line> findAllByPublish(String publish) {
		
		return this.lineDao.findAllByPublish(publish);
	}

	@Override
	public String findLineForTree() {
		List<Line> lines = lineDao.findAllByOrder();
		if(lines!=null && lines.size()>0){
			String jsonString = "";
			for(int i=0,len=lines.size(); i< len; i++){
				jsonString +="{id:";
			}
		}
		return null;
	}

	@Override
	public List<String> showVersion() {
		return lineDao.findAllVersion();
	}

	@Override
	public List<Station> sortStations(Set<Station> stations) {
		List<Station> sortList = null;
		if (stations != null && stations.size() > 0) {
			sortList = new ArrayList<Station>();
			Map<Integer, Station> map = new HashMap<Integer, Station>();
			Iterator<Station> it = stations.iterator();
			int[] array = new int[stations.size()];
			int i = 0;
			while (it.hasNext()) {
				Station station = it.next();
				map.put(Integer.valueOf(station.getCode()), station);
				array[i] = Integer.valueOf(station.getCode());
				i++;
			}
			
			array = SortUtils.quickSort(array, 0, array.length-1);
			for(int j=0,len=array.length;j<len; j++){
				sortList.add(map.get(array[j]));
			}
			
		}
		return sortList;
	}
	
	@Override
	public void saveLine(Line line) {
		lineDao.saveLine(line);
	}

	@Override
	public Line findById(String id) {
		
		return lineDao.findById(id);
	}

	@Override
	public boolean isUniquene(String id,String name,String code,String version) {
		List<Line> lines = lineDao.findByCondition(id, name, code, version);
		if(lines==null || lines.size()<1) return true;
		return false;
	}

	@Override
	public void deleteLine(String id) {
		lineDao.deleteById(id);
	}

	@Override
	public List<Line> findByVersion(String version) {
		
		return lineDao.findByVersion(version);
	}

	@Override
	public void publish(String version) {
		lineDao.publish(version);
	}

	@Override
	public int findLasetVersion() {
		return lineDao.findLasetVersion();
	}

	@Override
	public boolean isUnqueneByVersionAndCodeId(String id, String version,String codeId) {
		List<Line> list = this.lineDao.findByVersionAndCodeId(id, version, codeId);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = this.lineDao.findMaxCodeId(version);
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

    @Override
    public Line findByCode(String lineCode) {
        return lineDao.findByCode(lineCode);
    }


}
