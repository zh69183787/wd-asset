package com.wonders.webservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wonders.utils.DateAdapter;
import org.hibernate.annotations.GenericGenerator;



/**
 * TDamageAsset entity. @author MyEclipse Persistence Tools
 */


@Entity
@Table(name = "t_attachment_tmp")
@XmlRootElement
public class AttachmentDto implements java.io.Serializable{
	private String id;
	private String attachmentId;
	private String fileTitle;   
	private String fileType;
	private String fileRoute;
	private Date createTime;
	private String creator;
	private String assetNo;
	private Date uploadDate;
	private String type;
	private String objectId;
	
	
	@Column(name = "object_id")
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	
	@Id
	 @GeneratedValue(generator="idGenerator")
	 @GenericGenerator(name="idGenerator", strategy="uuid")
	 @Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "UPLOAD_DATE", length = 7)
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	 @Column(name = "attachment_id")
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	@Column(name="asset_No")
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	
	@Column(name="file_Title")
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	@Column(name="file_Type")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name="file_Route")
	public String getFileRoute() {
		return fileRoute;
	}
	public void setFileRoute(String fileRoute) {
		this.fileRoute = fileRoute;
	}
	
	@Column(name="create_time")
    @XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="creator")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetNo == null) ? 0 : assetNo.hashCode());
		result = prime * result
				+ ((attachmentId == null) ? 0 : attachmentId.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((fileRoute == null) ? 0 : fileRoute.hashCode());
		result = prime * result
				+ ((fileTitle == null) ? 0 : fileTitle.hashCode());
		result = prime * result
				+ ((fileType == null) ? 0 : fileType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttachmentDto other = (AttachmentDto) obj;
		if (assetNo == null) {
			if (other.assetNo != null)
				return false;
		} else if (!assetNo.equals(other.assetNo))
			return false;
		if (attachmentId == null) {
			if (other.attachmentId != null)
				return false;
		} else if (!attachmentId.equals(other.attachmentId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (fileRoute == null) {
			if (other.fileRoute != null)
				return false;
		} else if (!fileRoute.equals(other.fileRoute))
			return false;
		if (fileTitle == null) {
			if (other.fileTitle != null)
				return false;
		} else if (!fileTitle.equals(other.fileTitle))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		return true;
	}
	
	
	
	
}
