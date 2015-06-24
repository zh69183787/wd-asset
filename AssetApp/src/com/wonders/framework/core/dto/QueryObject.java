package com.wonders.framework.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 封装查询的条件以及其他一些查询信息
 * 
 * @author cheney
 * 
 */
@SuppressWarnings({ "serial", "unchecked" })
public class QueryObject implements Serializable{	

	/**
	 * 存放Criteria,criteria之间用or连接
	 */
	protected List<Criteria> oredCriteriaList = new ArrayList();
	
	/**
	 * 存放Criteria,criteria之间用or连接
	 */
	protected List<OrderBy> orderByList = new ArrayList();
	
	/**
	 * 得到OredCriteria列表
	 * 
	 * @return
	 */
	public List<Criteria> getOredCriteriaList() {
		return oredCriteriaList;
	}
	
	/**
	 * 得到OrderBy列表
	 * 
	 * @return
	 */
	public List<OrderBy> getOderByList() {
		return orderByList;
	}

	/**
	 * 创建一个新的Criteria,如果不是第一个criteria,那么这个Criteria与原来老的Criteria用or连接
	 * 
	 * @return
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		// if (oredCriteria.size() == 0) {
		oredCriteriaList.add(criteria);
		// }
		return criteria;
	}

	/**
	 * 创建一个Criteria
	 * 
	 * @return
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * 字段的各种常用关系
	 * 
	 * @author cheney
	 * 
	 */
	/*
	private static enum RelationType {
		IsNull, IsNotNull, EqualsTo, NotEqualsTo, GreaterThan, GreaterThanOrEqualsTo, LessThan, LessThanOrEqualsTo, Like, NotLike, In, NotIn, Between, NotBetween
	}	
	*/

	/**
	 * OrderBy封装
	 * 
	 * @author cheney
	 * 
	 */
	public static class OrderBy implements Serializable{
		protected String propertity;
		protected String orderByType;
		protected String column;
		public String getPropertity() {
			return propertity;
		}
		public void setPropertity(String propertity) {
			this.propertity = propertity;
		}
		public String getOrderByType() {
			return orderByType;
		}
		public void setOrderByType(String orderByType) {
			this.orderByType = orderByType;
		}
		public String getColumn() {
			return column;
		}
		public void setColumn(String column) {
			this.column = column;
		}		
	}
	
	
	
	/**
	 * 添加一个属性升序排列
	 * 
	 * @param propertity
	 */
	public void addOrderByAsc(String propertity) {
		OrderBy orderBy = new OrderBy();
		orderBy.setPropertity(propertity);
		orderBy.setOrderByType(" asc");
		orderByList.add(orderBy);
	}
	
	/**
	 * 添加一个属性降序排列
	 * 
	 * @param propertity
	 */
	public void addOrderByDesc(String propertity) {
		OrderBy orderBy = new OrderBy();
		orderBy.setPropertity(propertity);
		orderBy.setOrderByType(" desc");
		orderByList.add(orderBy);
	}

	/**
	 * Criteria参数
	 * 
	 * @author cheney
	 * 
	 */
	public static class CriteriaParameter implements Serializable{
		protected String propertity;		
		protected String operator;
		protected Object values;	
		protected String column;

		public String getOperator() {				
			return operator;
		}	

		protected void setOperator(String operator) {
			this.operator = operator;
		}

		public Object getValues() {
			return values;
		}

		protected void setValues(Object values) {
			this.values = values;
		}

		public String getPropertity() {
			return propertity;
		}

		protected void setPropertity(String propertity) {
			this.propertity = propertity;
		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}		
	}

	
	/**
	 * Criteria，用于查询条件的封装
	 * 
	 * @author cheney
	 * 
	 */
	public static class Criteria implements Serializable{		
		
		protected List<CriteriaParameter> criteriaWithoutValue;

		protected List<CriteriaParameter> criteriaWithSingleValue;

		protected List<CriteriaParameter> criteriaWithListValue;

		protected List<CriteriaParameter> criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList<CriteriaParameter>();
			criteriaWithSingleValue = new ArrayList<CriteriaParameter>();
			criteriaWithListValue = new ArrayList<CriteriaParameter>();
			criteriaWithBetweenValue = new ArrayList<CriteriaParameter>();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List<CriteriaParameter> getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List<CriteriaParameter> getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List<CriteriaParameter> getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List<CriteriaParameter> getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterionWithoutValue(CriteriaParameter parameter) {
			criteriaWithoutValue.add(parameter);
		}
		
		protected void addCriterionWithSingleValue(CriteriaParameter parameter) {
			if (parameter.getValues() == null) {
				throw new RuntimeException("values or values for " + parameter.getPropertity()
						+ " cannot be null");
			}
			criteriaWithSingleValue.add(parameter);
		}
		
		protected void addCriterionWithListValue(CriteriaParameter parameter) {
			List<Object> valueList = (List<Object>) parameter.getValues();
			if (valueList == null || valueList.size() == 0) {
				throw new RuntimeException("values or values for " + parameter.getPropertity()
						+ " cannot be null or empty");
			}
			criteriaWithListValue.add(parameter);
		}
		
		protected void addCriterionWithBetweenValue(CriteriaParameter parameter) {
			List<Object> valueList = (List<Object>) parameter.getValues();
			if (valueList == null || valueList.size() < 2 || valueList.get(0) == null || valueList.get(1) == null) {
				throw new RuntimeException("values or values for " + parameter.getPropertity()
						+ " cannot be null or empty");
			}
			criteriaWithBetweenValue.add(parameter);
		}

		public Criteria andIsNull(String propertity) {
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setOperator("is null");
			addCriterionWithoutValue(parameter);
			return this;
		}

		public Criteria andIsNotNull(String propertity) {
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setOperator("is not null");
			addCriterionWithoutValue(parameter);
			return this;
		}

		public Criteria andEqualTo(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("=");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andNotEqualTo(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("<>");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andGreaterThan(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator(">");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andGreaterThanOrEqualTo(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator(">=");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andLessThan(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("<");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andLessThanOrEqualTo(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("<=");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andLike(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("like");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andNotLike(String propertity, Object value) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(value);
			parameter.setOperator("not like");
			addCriterionWithSingleValue(parameter);
			return this;
		}

		public Criteria andIn(String propertity, List values) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(values);
			parameter.setOperator("in");
			addCriterionWithListValue(parameter);
			return this;
		}

		public Criteria andNotIn(String propertity, List values) {			
			CriteriaParameter parameter = new CriteriaParameter();
			parameter.setPropertity(propertity);
			parameter.setValues(values);
			parameter.setOperator("not in");
			addCriterionWithListValue(parameter);
			return this;
		}

		public Criteria andBetween(String propertity, Object value1,
				Object value2) {			
			CriteriaParameter parameter = new CriteriaParameter();
			List<Object> values = new ArrayList();
			values.add(value1);
			values.add(value2);
			parameter.setPropertity(propertity);
			parameter.setValues(values);
			parameter.setOperator("between");
			addCriterionWithBetweenValue(parameter);
			return this;
		}

		public Criteria andNotBetween(String propertity, Object value1,
				Object value2) {			
			CriteriaParameter parameter = new CriteriaParameter();
			List<Object> values = new ArrayList();
			values.add(value1);
			values.add(value2);
			parameter.setPropertity(propertity);
			parameter.setValues(values);
			parameter.setOperator("not between");
			addCriterionWithBetweenValue(parameter);
			return this;
		}

	}

}
