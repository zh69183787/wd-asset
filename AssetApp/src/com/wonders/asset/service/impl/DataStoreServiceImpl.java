package com.wonders.asset.service.impl;

import com.wonders.asset.service.DataStoreService;
import com.wonders.utils.Encryption;
import com.wonders.webservice.api.DataImportAPIDelegate;
import com.wonders.webservice.api.DataImportAPIService;
import com.wonders.webservice.dto.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.dom4j.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/11/5.
 */
public class DataStoreServiceImpl implements DataStoreService {

    private String password="eam2013!";

    private String userName="eam";

    @Override
    public List<Response> getDataFromStore(Class clz) throws DocumentException, JAXBException {

        List<Response> responseList = new ArrayList<Response>();
        String name = "upload"+StringUtils.substringBefore(clz.getSimpleName(),"Dto");
        DataImportAPIDelegate service = (new DataImportAPIService()).getDataImportAPIHandler();

        String result = service.getDataInfo(userName, Encryption.getMD5(password), name);
        Document resultDoc =  DocumentHelper.parseText(result);
        Node node  = resultDoc.selectSingleNode("//code");
        if(node!=null&&"1001".equals(node.getText())){
            List<Node> paramNodes = resultDoc.selectNodes("//param");
            for (Node paramNode : paramNodes) {
                Document dataDocument =  DocumentHelper.parseText(paramNode.selectSingleNode("content").getText());
                String date =  paramNode.selectSingleNode("date").getText();
                dataDocument = DocumentHelper.parseText(dataDocument.getRootElement().selectSingleNode("content").getText());
                ((Element)(dataDocument.getRootElement().elements().get(0))).setName("List");
                List<Element> elements = ((Element)(dataDocument.getRootElement().elements().get(0))).elements();
                for (Element element : elements) {
                    Element uploadDateElement = element.addElement("uploadDate");
                    uploadDateElement.setText(date);
                }

                InputStream inputStream = new ByteArrayInputStream(dataDocument.asXML().getBytes(Charset.forName("UTF-8")));
                JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                Response response = (Response) jaxbUnmarshaller.unmarshal(inputStream);
                response.setId(paramNode.selectSingleNode("id").getText());
                responseList.add(response);
            }
        }
        return responseList;
    }

    @Override
    public String setDataInStore(String content) throws DocumentException, JAXBException {
        DataImportAPIDelegate service = (new DataImportAPIService()).getDataImportAPIHandler();

        String result = service.setDataInfo(userName, Encryption.getMD5(password),  DateFormatUtils.format((new Date()), "yyyy-MM-dd"), content);
        System.out.println("=========================="+result);
        return result;

    }

    @Override
    public String confirmData(Class clz,String id) throws DocumentException {
        String name = "upload"+StringUtils.substringBefore(clz.getSimpleName(),"Dto");
        DataImportAPIDelegate service = (new DataImportAPIService()).getDataImportAPIHandler();
        String result = service.confirmGetDataInfo(userName, Encryption.getMD5(password), name,id);
        Document resultDoc =  DocumentHelper.parseText(result);
        Node node  = resultDoc.selectSingleNode("//code");

        return node.getText();//1002操作成功
    }

}
