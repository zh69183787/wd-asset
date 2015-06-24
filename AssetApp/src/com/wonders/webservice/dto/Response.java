package com.wonders.webservice.dto;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 * Created by Administrator on 2014/11/5.
 */
@XmlRootElement(name = "root")
public class Response {

    private String id;

    private List list;

    @XmlElementWrapper(name = "List")
    @XmlElements({
            @XmlElement(name="DamageAsset", type = DamageAssetDto.class),
            @XmlElement(name="AssetTask", type = AssetTaskDto.class),
            @XmlElement(name="AssetRecord", type = AssetRecordDto.class),   
            @XmlElement(name="BorrowAsset", type = BorrowAssetDto.class),
            @XmlElement(name="LandAsset", type = LandAssetDto.class),
            @XmlElement(name="HouseAsset", type = HouseAssetDto.class),
            @XmlElement(name="StopAsset", type = StopAssetDto.class),
            @XmlElement(name="DisableAsset", type = DisableAssetDto.class),    
            @XmlElement(name="AllocateAsset", type = AllocateAssetDto.class),
            @XmlElement(name="Attachment", type = AttachmentDto.class),
            @XmlElement(name="AssetTask", type = AssetTaskDto.class)
    })
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @XmlTransient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
