package com.wonders.asset.model.bo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import com.wonders.asset.model.dw.DwOverhaulLine;

/**
 * 大修更新改造按线路分布情况
 * 
 * @date 2013-12-11
 */
@SuppressWarnings("serial")
public class DwOverhaulLineBo implements Serializable {

	private String id;
	private String year;
	private String order_;
	private String name;
	private String overhaulPrice; // 大修费用
	private String overhaulVehicle;	//车辆
	private String overhaulPower;	//供电
	private String overhaulSignal;	//通号
	private String overhaulWork;	//工务
	private String overhaulLogistics;	//后勤
	private String overhaulStationEle;	//车站机电
	private String overhaulOperation1;	//运一
	private String overhaulOperation2;	//运二
	private String overhaulOperation3;	//运三
	private String overhaulOperation4;	//运四
	private String overhaulTransportManager;	//运管
	private String overhaulInformation;	//信息
	private String overhaulMaglev; 	//磁浮
	
	private String overhaulTotal ;		//维保小计：车辆+供电+通号+工务+后勤
	
	
	private String renovatePrice; // 更新改造费用
	private String renovateVehicle;	//车辆
	private String renovatePower;	//供电
	private String renovateSignal;	//通号
	private String renovateWork;	//工务
	private String renovateLogistics;	//后勤
	private String renovateStationEle;	//车站机电
	private String renovateOperation1;	//运一
	private String renovateOperation2;	//运二
	private String renovateOperation3;	//运三
	private String renovateOperation4;	//运四
	private String renovateTransportManager;	//运管
	private String renovateInformation;	//信息
	private String renovateMaglev; 	//磁浮
	
	private String renovateTotal ;		//维保小计：车辆+供电+通号+工务+后勤

	private Date createDate; // 创建时间
	
	public DwOverhaulLineBo(DwOverhaulLine line){
		this.name = line.getName();
		this.year = line.getYear();
		this.overhaulTotal = trans(line.getOverhaulVehicle()+line.getOverhaulPower()+line.getOverhaulSignal()+line.getOverhaulWork()+line.getOverhaulLogistics());
		this.renovateTotal = trans(line.getRenovateVehicle()+line.getRenovatePower()+line.getRenovateSignal()+line.getRenovateWork()+line.getRenovateLogistics());
		
		this.overhaulPrice = trans(line.getOverhaulPrice()); 
		this.overhaulVehicle = trans(line.getOverhaulVehicle());	
		this.overhaulPower =  trans(line.getOverhaulPower());	
		this.overhaulSignal =  trans(line.getOverhaulSignal());	
		this.overhaulWork =  trans(line.getOverhaulWork());
		this.overhaulLogistics =  trans(line.getOverhaulLogistics());	
		this.overhaulStationEle =  trans(line.getOverhaulStationEle());	
		this.overhaulOperation1 =  trans(line.getOverhaulOperation1());	
		this.overhaulOperation2 =  trans(line.getOverhaulOperation2());	
		this.overhaulOperation3 =  trans(line.getOverhaulOperation3());	
		this.overhaulOperation4 =  trans(line.getOverhaulOperation4());	
		this.overhaulTransportManager =  trans(line.getOverhaulTransportManager());	
		this.overhaulInformation =  trans(line.getOverhaulInformation());	
		this.overhaulMaglev =  trans(line.getOverhaulMaglev()); 	
		
		this.renovatePrice =  trans(line.getOverhaulMaglev()); 
		this.renovateVehicle =  trans(line.getRenovateVehicle());	
		this.renovatePower =  trans(line.getRenovatePower());	
		this.renovateSignal =  trans(line.getRenovateSignal());	
		this.renovateWork =  trans(line.getRenovateWork());	
		this.renovateLogistics =  trans(line.getRenovateLogistics());	
		this.renovateStationEle =  trans(line.getRenovateStationEle());	
		this.renovateOperation1 =  trans(line.getRenovateOperation1());	
		this.renovateOperation2 =  trans(line.getRenovateOperation2());	
		this.renovateOperation3 =  trans(line.getRenovateOperation3());	
		this.renovateOperation4 =  trans(line.getRenovateOperation4());	
		this.renovateTransportManager =  trans(line.getRenovateTransportManager());	
		this.renovateInformation =  trans(line.getRenovateInformation());	
		this.renovateMaglev =  trans(line.getRenovateMaglev()); 	
	};
	
	public String trans(Double d){
		if(d==null) return "";
		if(d==0) return "0";
		DecimalFormat df = new DecimalFormat("#####0.00");   
		return df.format((d/10000));
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOverhaulPrice() {
		return overhaulPrice;
	}

	public void setOverhaulPrice(String overhaulPrice) {
		this.overhaulPrice = overhaulPrice;
	}

	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order) {
		order_ = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRenovatePrice() {
		return renovatePrice;
	}

	public void setRenovatePrice(String renovatePrice) {
		this.renovatePrice = renovatePrice;
	}

	public String getOverhaulVehicle() {
		return overhaulVehicle;
	}

	public void setOverhaulVehicle(String overhaulVehicle) {
		this.overhaulVehicle = overhaulVehicle;
	}

	public String getOverhaulPower() {
		return overhaulPower;
	}

	public void setOverhaulPower(String overhaulPower) {
		this.overhaulPower = overhaulPower;
	}

	public String getOverhaulSignal() {
		return overhaulSignal;
	}

	public void setOverhaulSignal(String overhaulSignal) {
		this.overhaulSignal = overhaulSignal;
	}

	public String getOverhaulWork() {
		return overhaulWork;
	}

	public void setOverhaulWork(String overhaulWork) {
		this.overhaulWork = overhaulWork;
	}

	public String getOverhaulLogistics() {
		return overhaulLogistics;
	}

	public void setOverhaulLogistics(String overhaulLogistics) {
		this.overhaulLogistics = overhaulLogistics;
	}

	public String getOverhaulOperation1() {
		return overhaulOperation1;
	}

	public void setOverhaulOperation1(String overhaulOperation1) {
		this.overhaulOperation1 = overhaulOperation1;
	}

	public String getOverhaulOperation2() {
		return overhaulOperation2;
	}

	public void setOverhaulOperation2(String overhaulOperation2) {
		this.overhaulOperation2 = overhaulOperation2;
	}

	public String getOverhaulOperation3() {
		return overhaulOperation3;
	}

	public void setOverhaulOperation3(String overhaulOperation3) {
		this.overhaulOperation3 = overhaulOperation3;
	}

	public String getOverhaulOperation4() {
		return overhaulOperation4;
	}

	public void setOverhaulOperation4(String overhaulOperation4) {
		this.overhaulOperation4 = overhaulOperation4;
	}

	public String getOverhaulTransportManager() {
		return overhaulTransportManager;
	}

	public void setOverhaulTransportManager(String overhaulTransportManager) {
		this.overhaulTransportManager = overhaulTransportManager;
	}

	public String getOverhaulInformation() {
		return overhaulInformation;
	}

	public void setOverhaulInformation(String overhaulInformation) {
		this.overhaulInformation = overhaulInformation;
	}

	public String getOverhaulMaglev() {
		return overhaulMaglev;
	}

	public void setOverhaulMaglev(String overhaulMaglev) {
		this.overhaulMaglev = overhaulMaglev;
	}

	public String getRenovateVehicle() {
		return renovateVehicle;
	}

	public void setRenovateVehicle(String renovateVehicle) {
		this.renovateVehicle = renovateVehicle;
	}

	public String getRenovatePower() {
		return renovatePower;
	}

	public void setRenovatePower(String renovatePower) {
		this.renovatePower = renovatePower;
	}

	public String getRenovateSignal() {
		return renovateSignal;
	}

	public void setRenovateSignal(String renovateSignal) {
		this.renovateSignal = renovateSignal;
	}

	public String getRenovateWork() {
		return renovateWork;
	}

	public void setRenovateWork(String renovateWork) {
		this.renovateWork = renovateWork;
	}

	public String getRenovateLogistics() {
		return renovateLogistics;
	}

	public void setRenovateLogistics(String renovateLogistics) {
		this.renovateLogistics = renovateLogistics;
	}

	public String getRenovateOperation1() {
		return renovateOperation1;
	}

	public void setRenovateOperation1(String renovateOperation1) {
		this.renovateOperation1 = renovateOperation1;
	}

	public String getRenovateOperation2() {
		return renovateOperation2;
	}

	public void setRenovateOperation2(String renovateOperation2) {
		this.renovateOperation2 = renovateOperation2;
	}

	public String getRenovateOperation3() {
		return renovateOperation3;
	}

	public void setRenovateOperation3(String renovateOperation3) {
		this.renovateOperation3 = renovateOperation3;
	}

	public String getRenovateOperation4() {
		return renovateOperation4;
	}

	public void setRenovateOperation4(String renovateOperation4) {
		this.renovateOperation4 = renovateOperation4;
	}

	public String getRenovateTransportManager() {
		return renovateTransportManager;
	}

	public void setRenovateTransportManager(String renovateTransportManager) {
		this.renovateTransportManager = renovateTransportManager;
	}

	public String getRenovateInformation() {
		return renovateInformation;
	}

	public void setRenovateInformation(String renovateInformation) {
		this.renovateInformation = renovateInformation;
	}

	public String getRenovateMaglev() {
		return renovateMaglev;
	}

	public void setRenovateMaglev(String renovateMaglev) {
		this.renovateMaglev = renovateMaglev;
	}

	public String getOverhaulStationEle() {
		return overhaulStationEle;
	}

	public void setOverhaulStationEle(String overhaulStationEle) {
		this.overhaulStationEle = overhaulStationEle;
	}

	public String getRenovateStationEle() {
		return renovateStationEle;
	}

	public void setRenovateStationEle(String renovateStationEle) {
		this.renovateStationEle = renovateStationEle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOverhaulTotal() {
		return overhaulTotal;
	}

	public void setOverhaulTotal(String overhaulTotal) {
		this.overhaulTotal = overhaulTotal;
	}

	public String getRenovateTotal() {
		return renovateTotal;
	}

	public void setRenovateTotal(String renovateTotal) {
		this.renovateTotal = renovateTotal;
	}

	
	
}
