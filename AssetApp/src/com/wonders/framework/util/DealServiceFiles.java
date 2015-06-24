package com.wonders.framework.util;

import java.io.*;
import java.util.List; 
import org.apache.log4j.Logger;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//import org.jdom.output.*;

/**
 * 提供方法读取某个目录下的所有xml文件并合并到一个xml中
 * 
 * @author zhangdingsheng
 *
 */
public class DealServiceFiles {
	
	public static final Logger logger = Logger.getLogger("DealServiceFiles");
	
//	public void ReadAllFileToOneFile() {
//    	Document document = null;
//    	try {
//    		String path = DealServiceFiles.class.getClassLoader().getResource("").getPath()
//    				+ "InfoExchange";
//    		logger.info("----- ReadAllFileToOneFile path:" + path);
//    		logger.info("+++++++ deleteFlag:"
//    				+ deleteFile(path + "/" + "service-config.xml"));
//
//    		File file = new File(path);
//
//    		//返回该文件夹下所有文件的名称列表
//    		String[] name = file.list();
//    		String[] files = new String[name.length];
//
//    		for(int i=0;i<name.length;i++){
//    			if(name[i].length() > 4) {
//    				//比较名称后四位是否是.xml如果是则开启新的线程来处理文件
//					if(name[i].substring(name[i].length()-4,name[i].length())
//							.equals(".xml")){
////						File fl = new File(name[i]);
////						System.out.println("fl name=" + fl.getName());
//						files[i] = path + "/" + name[i];
//						logger.info("=== files[" + i + "]:"+ files[i]);
//					}
//    			}
//    		}
//
//    		SAXBuilder dbf = new SAXBuilder();
//    		document = (Document) dbf.build(files[0]);
//            Element docRoot = document.getRootElement();
//
//            for (int i = 1; i < files.length; i++) {
//                Document tmpdoc = dbf.build(files[i]);
//                List nlt = tmpdoc.getRootElement().getChildren();
//                for (int j = 0; j < nlt.size(); ) {
//                    Element el = (Element)nlt.get(0);
//                    // get free element
//                    el.detach();
//                    docRoot.addContent(el);
//                }
//            }
//
//          //You can process that document now.
//          Format f = Format.getCompactFormat();
//          f.setEncoding("UTF-8");
//          f.setIndent("  ");//每一层元素缩排两格
//
//          XMLOutputter XMLOut = new XMLOutputter(f);
//          XMLOut.output(document, new FileOutputStream(
//        		  path + "/" + "service-config.xml"));
//
//    	} catch (Exception e) {
//    		logger.error("=== ReadAllFileToOneFile error:" + e.getMessage());
//        }
//    }
//
//	public boolean deleteFile(String sPath) {
//		boolean flag = false;
//		File file = new File(sPath);
//		// 路径为文件且不为空则进行删除
//		if (file.isFile() && file.exists()) {
//			file.delete();
//			flag = true;
//		}
//		return flag;
//	}

}