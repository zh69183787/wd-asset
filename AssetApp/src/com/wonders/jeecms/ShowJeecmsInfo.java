
package com.wonders.jeecms;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowJeecmsInfo {

	private String appName="";
	private String secret="";       
	private String urlCa="";
	private String serverPath="";
	private String apiName="";
	private String token="";
	
	
	public ShowJeecmsInfo(HttpServletRequest request,HttpServletResponse response) {
		Properties properties = new Properties();
		String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		
		try {
			FileInputStream fis = new FileInputStream(configPath);
			properties.load(fis);			 
			this.setAppName(properties.getProperty("appName"));
			this.setSecret(properties.getProperty("secret"));
			this.setUrlCa(properties.getProperty("urlCa"));
			this.setServerPath(properties.getProperty("serverPath"));
			this.setApiName(properties.getProperty("apiName"));	
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setToken(getCookieValue(request,"token"));
	
	}
	public JSONArray getInfoList(String method,String params){
		JSONArray dataArray=null;
		String dataParams="<?xml version=\"1.0\" encoding=\"utf-8\"?><params>"+getParamsXMl(params)+"</params>";
		URL url = null;
		HttpURLConnection http = null;
		String textEntity="";
		try {
			url = new URL(urlCa+serverPath+"/"+apiName);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");		
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//http.connect();
			String param = "&appName=" + appName + "&token=" + token + "&method="+method+"&dataType=json&dataParams=" + dataParams + "&sign=" + getSign(method);

         	OutputStreamWriter osw=new OutputStreamWriter(http.getOutputStream(),"utf-8");
          	osw.write(param);
          	osw.flush();
          	osw.close();
			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();    	       			
				
				JSONObject callbackObj=JSONObject.fromObject(textEntity);
				if(callbackObj.getString("code").equals("100")){	
					Object dataObj=callbackObj.get("params");
					dataArray=JSONArray.fromObject(dataObj);
					if(dataArray.size()==1){
						JSONObject rec=dataArray.getJSONObject(0);
						String sRec=rec.getString("param");
						if(sRec.indexOf("{")!=-1){
							dataArray=JSONArray.fromObject(rec.get("param"));
						}
					}													
				}								
			}
		} catch (Exception e) {

		} finally {
			if (http != null) http.disconnect();
		}
		if(dataArray!=null)
			System.out.println("method="+method+",showJeecmsInfo.dataArray="+dataArray.toString().length());		
		return dataArray;
	}
   	
	private String getParamsXMl(String params){
		String xmlString="";
		JSONObject json=JSONObject.fromObject(params);
		Iterator keyIter=json.keys();
		String key="",value="";
		while(keyIter.hasNext()){
			key=(String)keyIter.next();
			value=json.getString(key);
			xmlString+="<"+key+">"+value+"</"+key+">";
		}
		
		return xmlString;

	}
	
	private String getCookieValue(HttpServletRequest request,String cookieName){
		Cookie[] cookies = request.getCookies();
		String token=null;
		if(cookies !=null){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];				
				if(cookieName.equals(cookie.getName())){
					try{
						token=java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				
				
			}
		}
		
		return token;
	}
	
	private String getMD5(String value) {  
        String result = null;  
        try{  
            byte[] valueByte = value.getBytes();  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(valueByte);  
            result = toHex(md.digest());  
        }catch(NoSuchAlgorithmException e1){  
            e1.printStackTrace();  
        }  
        return result;  
    }  

    // 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    } 

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getUrlCa() {
		return urlCa;
	}


	public void setUrlCa(String urlCa) {
		this.urlCa = urlCa;
	}


	public String getServerPath() {
		return serverPath;
	}


	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}


	public String getApiName() {
		return apiName;
	}


	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
	public String getSign(String method) {
		return getMD5(appName+token+method+secret);
	}

	
}
