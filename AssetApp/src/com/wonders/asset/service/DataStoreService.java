package com.wonders.asset.service;

import com.wonders.webservice.dto.Response;
import org.dom4j.DocumentException;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Administrator on 2014/11/5.
 */
public interface DataStoreService {

    List<Response> getDataFromStore(Class clz) throws DocumentException, JAXBException;


    String setDataInStore(String content) throws DocumentException, JAXBException;

    String confirmData(Class clz,String id) throws DocumentException, JAXBException;
}
