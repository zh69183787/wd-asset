
package com.wonders.asset.model.bo;

import java.io.Serializable;


public class AssetTaskVo implements Serializable {

	private String id; // id

	private String checkpersonlist; // checkpersonlist

	private String completerate; // completerate

	private String endtime; // endtime

	private String errornum; // errornum

	private String realitytime; // realitytime

	private String starttime; // starttime

	private String taskmemo; // taskmemo

	private String taskname; // taskname

	private String taskstatus; // taskstatus

	private String taskuser; // taskuser

	private String taskmemoFilter;
	
	private String operateDate;
	private String removed;
	
	private String finishRate;
	
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCheckpersonlist(String checkpersonlist) {
		this.checkpersonlist = checkpersonlist;
	}

	public String getCheckpersonlist() {
		return checkpersonlist;
	}

	public void setCompleterate(String completerate) {
		this.completerate = completerate;
	}

	public String getCompleterate() {
		return completerate;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setErrornum(String errornum) {
		this.errornum = errornum;
	}

	public String getErrornum() {
		return errornum;
	}

	public void setRealitytime(String realitytime) {
		this.realitytime = realitytime;
	}

	public String getRealitytime() {
		return realitytime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setTaskmemo(String taskmemo) {
		this.taskmemo = taskmemo;
	}

	public String getTaskmemo() {
		return taskmemo;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	public String getTaskuser() {
		return taskuser;
	}

	public String getTaskmemoFilter() {
		return taskmemoFilter;
	}

	public void setTaskmemoFilter(String taskmemoFilter) {
		this.taskmemoFilter = taskmemoFilter;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getFinishRate() {
		return finishRate;
	}

	public void setFinishRate(String finishRate) {
		this.finishRate = finishRate;
	}

	
	
}
