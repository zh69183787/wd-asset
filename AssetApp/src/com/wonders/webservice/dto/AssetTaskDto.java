package com.wonders.webservice.dto;

import com.wonders.asset.model.AssetCheckinfo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/11/7.
 */
@Entity
@Table(name = "T_ASSET_TASK_TMP")
@XmlRootElement(name = "AssetTask")
@SuppressWarnings("serial")
public class AssetTaskDto implements Serializable {

    private String taskId;

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
    private Date uploadDate;

    private List<AssetCheckinfoDto> assetCheckinfoList;


    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "TASK_ID", unique = true, nullable = false, length = 32)
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Column(name = "UPLOAD_DATE", length = 7)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

   @Transient
    @XmlElementWrapper(name = "AssetList")
    @XmlElements({
            @XmlElement(name="Asset", type = AssetCheckinfoDto.class)
    })
    public List<AssetCheckinfoDto> getAssetCheckinfoList() {
        return assetCheckinfoList;
    }

    public void setAssetCheckinfoList(List<AssetCheckinfoDto> assetCheckinfoList) {
        this.assetCheckinfoList = assetCheckinfoList;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "EAMTASKID")
    public String getId() {
        return id;
    }

    public void setCheckpersonlist(String checkpersonlist) {
        this.checkpersonlist = checkpersonlist;
    }

    @Column(name = "CHECKPERSONLIST", nullable = true, length = 800)
    public String getCheckpersonlist() {
        return checkpersonlist;
    }

    public void setCompleterate(String completerate) {
        this.completerate = completerate;
    }

    @Column(name = "COMPLETERATE", nullable = true, length = 20)
    public String getCompleterate() {
        return completerate;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Column(name = "ENDTIME", nullable = true, length = 20)
    public String getEndtime() {
        return endtime;
    }

    public void setErrornum(String errornum) {
        this.errornum = errornum;
    }

    @Column(name = "ERRORNUM", nullable = true, length = 50)
    public String getErrornum() {
        return errornum;
    }

    public void setRealitytime(String realitytime) {
        this.realitytime = realitytime;
    }

    @Column(name = "REALITYTIME", nullable = true, length = 20)
    public String getRealitytime() {
        return realitytime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    @Column(name = "STARTTIME", nullable = true, length = 20)
    public String getStarttime() {
        return starttime;
    }

    public void setTaskmemo(String taskmemo) {
        this.taskmemo = taskmemo;
    }

    @Column(name = "TASKMEMO", nullable = true, length = 400)
    public String getTaskmemo() {
        return taskmemo;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    @Column(name = "TASKNAME", nullable = true, length = 200)
    public String getTaskname() {
        return taskname;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    @Column(name = "TASKSTATUS", nullable = true, length = 50)
    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskuser(String taskuser) {
        this.taskuser = taskuser;
    }

    @Column(name = "TASKUSER", nullable = true, length = 60)
    public String getTaskuser() {
        return taskuser;
    }

    @Column(name = "TASKMEMO_FILTER ", nullable = true, length = 400)
    public String getTaskmemoFilter() {
        return taskmemoFilter;
    }

    public void setTaskmemoFilter(String taskmemoFilter) {
        this.taskmemoFilter = taskmemoFilter;
    }

    @Column(name = "OPERATE_DATE", nullable = true, length = 20)
    @XmlElement(name="operatorDate")
    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    @Column(name = "REMOVED", nullable = true, length = 2)
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }


}
