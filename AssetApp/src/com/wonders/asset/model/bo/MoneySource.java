package com.wonders.asset.model.bo;

import java.io.Serializable;

/**
 * 项目资金来源
 * 
 * @author Kai Yao
 * @date 2014-01-14
 */
@SuppressWarnings("serial")
public class MoneySource implements Serializable {
	private String unitId;
	private String unitName;
	private String lineName;
	private Double money;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
