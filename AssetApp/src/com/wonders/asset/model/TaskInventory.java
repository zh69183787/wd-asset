package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 资产盘点
 */
@Entity
@Table(name="TASK_INVENTORY")
public class TaskInventory {

	private String taskId; 	//任务ID
	private String taskName;	//任务名称
	private String checkPersionList;	//指定盘点人
	private String taskUser;	//任务创建人
	private Date startTime;	//任务开始时间
	private Date endTime;	//任务结束时间
	private String taskMemo;	//任务范围
	private Date realityTime;	//实际盘点时间
	private String taskStatus;	//任务状态
	private String completerRate;	//完成率
	private String errorNum;	//出错数量
	private Date operateDate;	//操作日期
	private Date createDate;	//创建时间
	
	@Id
	@Column(name="TASK_ID",nullable=false,length=40)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Column(name="TASK_NAME")
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Column(name="CHECK_PERSION_LIST")
	public String getCheckPersionList() {
		return checkPersionList;
	}
	public void setCheckPersionList(String checkPersionList) {
		this.checkPersionList = checkPersionList;
	}
	
	@Column(name="TASK_USER")
	public String getTaskUser() {
		return taskUser;
	}
	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="TASK_MEMO")
	public String getTaskMemo() {
		return taskMemo;
	}
	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REALITY_TIME")
	public Date getRealityTime() {
		return realityTime;
	}
	public void setRealityTime(Date realityTime) {
		this.realityTime = realityTime;
	}
	
	@Column(name="TASK_STATUS")
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@Column(name="COMPLETER_RATE")
	public String getCompleterRate() {
		return completerRate;
	}
	public void setCompleterRate(String completerRate) {
		this.completerRate = completerRate;
	}
	
	@Column(name="ERROR_NUM")
	public String getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATE_DATE")
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}


