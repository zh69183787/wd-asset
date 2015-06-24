package com.wonders.asset.model.dw;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大修更新改造按线路分布情况
 * 
 * @author Kai Yao
 * @date 2013-12-11
 */
@Entity
@Table(name = "DW_OVERHAUL_LINE")
@SuppressWarnings("serial")
public class DwOverhaulLine implements Serializable {

	private String id;
	private String year;
	private String order_;
	private String name;
	private Double overhaulPrice; // 大修费用
	private Double overhaulVehicle;	//车辆
	private Double overhaulPower;	//供电
	private Double overhaulSignal;	//通号
	private Double overhaulWork;	//工务
	private Double overhaulLogistics;	//后勤
	private Double overhaulStationEle;	//车站机电
	private Double overhaulOperation1;	//运一
	private Double overhaulOperation2;	//运二
	private Double overhaulOperation3;	//运三
	private Double overhaulOperation4;	//运四
	private Double overhaulTransportManager;	//运管
	private Double overhaulInformation;	//信息
	private Double overhaulMaglev; 	//磁浮
	
	private Double renovatePrice; // 更新改造费用
	private Double renovateVehicle;	//车辆
	private Double renovatePower;	//供电
	private Double renovateSignal;	//通号
	private Double renovateWork;	//工务
	private Double renovateLogistics;	//后勤
	private Double renovateStationEle;	//车站机电
	private Double renovateOperation1;	//运一
	private Double renovateOperation2;	//运二
	private Double renovateOperation3;	//运三
	private Double renovateOperation4;	//运四
	private Double renovateTransportManager;	//运管
	private Double renovateInformation;	//信息
	private Double renovateMaglev; 	//磁浮

	private Date createDate; // 创建时间
	
	public DwOverhaulLine(){
		this.overhaulPrice = 0d; 
		this.overhaulVehicle = 0d;	
		this.overhaulPower = 0d;	
		this.overhaulSignal = 0d;	
		this.overhaulWork = 0d;
		this.overhaulLogistics = 0d;	
		this.overhaulStationEle = 0d;	
		this.overhaulOperation1 = 0d;	
		this.overhaulOperation2 = 0d;	
		this.overhaulOperation3 = 0d;	
		this.overhaulOperation4 = 0d;	
		this.overhaulTransportManager = 0d;	
		this.overhaulInformation = 0d;	
		this.overhaulMaglev = 0d; 	
		
		this.renovatePrice = 0d; 
		this.renovateVehicle = 0d;	
		this.renovatePower = 0d;	
		this.renovateSignal = 0d;	
		this.renovateWork = 0d;	
		this.renovateLogistics = 0d;	
		this.renovateStationEle = 0d;	
		this.renovateOperation1 = 0d;	
		this.renovateOperation2 = 0d;	
		this.renovateOperation3 = 0d;	
		this.renovateOperation4 = 0d;	
		this.renovateTransportManager = 0d;	
		this.renovateInformation = 0d;	
		this.renovateMaglev = 0d; 	
	};
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "OVERHAUL_PRICE")
	public Double getOverhaulPrice() {
		return overhaulPrice;
	}

	public void setOverhaulPrice(Double overhaulPrice) {
		this.overhaulPrice = overhaulPrice;
	}

	@Column(name = "ORDER_", length = 40)
	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order) {
		order_ = order;
	}

	@Column(name = "NAME", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RENOVATE_PRICE")
	public Double getRenovatePrice() {
		return renovatePrice;
	}

	public void setRenovatePrice(Double renovatePrice) {
		this.renovatePrice = renovatePrice;
	}

	@Column(name = "OVERHAUL_VEHICLE")
	public Double getOverhaulVehicle() {
		return overhaulVehicle;
	}

	public void setOverhaulVehicle(Double overhaulVehicle) {
		this.overhaulVehicle = overhaulVehicle;
	}

	@Column(name = "OVERHAUL_POWER")
	public Double getOverhaulPower() {
		return overhaulPower;
	}

	public void setOverhaulPower(Double overhaulPower) {
		this.overhaulPower = overhaulPower;
	}

	@Column(name = "OVERHAUL_SIGNAL")
	public Double getOverhaulSignal() {
		return overhaulSignal;
	}

	public void setOverhaulSignal(Double overhaulSignal) {
		this.overhaulSignal = overhaulSignal;
	}

	@Column(name = "OVERHAUL_WORK")
	public Double getOverhaulWork() {
		return overhaulWork;
	}

	public void setOverhaulWork(Double overhaulWork) {
		this.overhaulWork = overhaulWork;
	}

	@Column(name = "OVERHAUL_LOGISTICS")
	public Double getOverhaulLogistics() {
		return overhaulLogistics;
	}

	public void setOverhaulLogistics(Double overhaulLogistics) {
		this.overhaulLogistics = overhaulLogistics;
	}

	@Column(name = "OVERHAUL_OPERATION1")
	public Double getOverhaulOperation1() {
		return overhaulOperation1;
	}

	public void setOverhaulOperation1(Double overhaulOperation1) {
		this.overhaulOperation1 = overhaulOperation1;
	}

	@Column(name = "OVERHAUL_OPERATION2")
	public Double getOverhaulOperation2() {
		return overhaulOperation2;
	}

	public void setOverhaulOperation2(Double overhaulOperation2) {
		this.overhaulOperation2 = overhaulOperation2;
	}

	@Column(name = "OVERHAUL_OPERATION3")
	public Double getOverhaulOperation3() {
		return overhaulOperation3;
	}

	public void setOverhaulOperation3(Double overhaulOperation3) {
		this.overhaulOperation3 = overhaulOperation3;
	}

	@Column(name = "OVERHAUL_OPERATION4")
	public Double getOverhaulOperation4() {
		return overhaulOperation4;
	}

	public void setOverhaulOperation4(Double overhaulOperation4) {
		this.overhaulOperation4 = overhaulOperation4;
	}

	@Column(name = "OVERHAUL_TRANSPORT_MANAGER")
	public Double getOverhaulTransportManager() {
		return overhaulTransportManager;
	}

	public void setOverhaulTransportManager(Double overhaulTransportManager) {
		this.overhaulTransportManager = overhaulTransportManager;
	}

	@Column(name = "OVERHAUL_INFORMATION")
	public Double getOverhaulInformation() {
		return overhaulInformation;
	}

	public void setOverhaulInformation(Double overhaulInformation) {
		this.overhaulInformation = overhaulInformation;
	}

	@Column(name = "OVERHAUL_MAGLEV")
	public Double getOverhaulMaglev() {
		return overhaulMaglev;
	}

	public void setOverhaulMaglev(Double overhaulMaglev) {
		this.overhaulMaglev = overhaulMaglev;
	}

	@Column(name = "renovate_Vehicle")
	public Double getRenovateVehicle() {
		return renovateVehicle;
	}

	public void setRenovateVehicle(Double renovateVehicle) {
		this.renovateVehicle = renovateVehicle;
	}

	@Column(name = "RENOVATE_POWER")
	public Double getRenovatePower() {
		return renovatePower;
	}

	public void setRenovatePower(Double renovatePower) {
		this.renovatePower = renovatePower;
	}

	@Column(name = "RENOVATE_SIGNAL")
	public Double getRenovateSignal() {
		return renovateSignal;
	}

	public void setRenovateSignal(Double renovateSignal) {
		this.renovateSignal = renovateSignal;
	}

	@Column(name = "RENOVATE_WORK")
	public Double getRenovateWork() {
		return renovateWork;
	}

	public void setRenovateWork(Double renovateWork) {
		this.renovateWork = renovateWork;
	}

	@Column(name = "RENOVATE_LOGISTICS")
	public Double getRenovateLogistics() {
		return renovateLogistics;
	}

	public void setRenovateLogistics(Double renovateLogistics) {
		this.renovateLogistics = renovateLogistics;
	}

	@Column(name = "RENOVATE_OPERATION1")
	public Double getRenovateOperation1() {
		return renovateOperation1;
	}

	public void setRenovateOperation1(Double renovateOperation1) {
		this.renovateOperation1 = renovateOperation1;
	}

	@Column(name = "RENOVATE_OPERATION2")
	public Double getRenovateOperation2() {
		return renovateOperation2;
	}

	public void setRenovateOperation2(Double renovateOperation2) {
		this.renovateOperation2 = renovateOperation2;
	}

	@Column(name = "RENOVATE_OPERATION3")
	public Double getRenovateOperation3() {
		return renovateOperation3;
	}

	public void setRenovateOperation3(Double renovateOperation3) {
		this.renovateOperation3 = renovateOperation3;
	}

	@Column(name = "RENOVATE_OPERATION4")
	public Double getRenovateOperation4() {
		return renovateOperation4;
	}

	public void setRenovateOperation4(Double renovateOperation4) {
		this.renovateOperation4 = renovateOperation4;
	}

	@Column(name = "RENOVATE_TRANSPORT_MANAGER")
	public Double getRenovateTransportManager() {
		return renovateTransportManager;
	}

	public void setRenovateTransportManager(Double renovateTransportManager) {
		this.renovateTransportManager = renovateTransportManager;
	}

	@Column(name = "RENOVATE_INFORMATION")
	public Double getRenovateInformation() {
		return renovateInformation;
	}

	public void setRenovateInformation(Double renovateInformation) {
		this.renovateInformation = renovateInformation;
	}

	@Column(name = "RENOVATE_MAGLEV")
	public Double getRenovateMaglev() {
		return renovateMaglev;
	}

	public void setRenovateMaglev(Double renovateMaglev) {
		this.renovateMaglev = renovateMaglev;
	}

	@Column(name = "OVERHAUL_STATION_ELE")
	public Double getOverhaulStationEle() {
		return overhaulStationEle;
	}

	public void setOverhaulStationEle(Double overhaulStationEle) {
		this.overhaulStationEle = overhaulStationEle;
	}

	@Column(name = "RENOVATE_STATION_ELE")
	public Double getRenovateStationEle() {
		return renovateStationEle;
	}

	public void setRenovateStationEle(Double renovateStationEle) {
		this.renovateStationEle = renovateStationEle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
