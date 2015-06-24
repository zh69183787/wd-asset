package com.wonders.asset.model.bo;

import java.io.Serializable;

/**
 * 个数和价值的 键值对
 * 
 * @author Kai Yao
 * @date 2013-11-12
 */
@SuppressWarnings("serial")
public class CountPrice implements Serializable {
	private String count;
	private String price;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
