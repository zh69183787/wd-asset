package com.wonders.framework.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;


/**
 * 提供方法解析配置文件提供不同方式的业务接口
 *
 * @author cheney
 *
 */
public class ServiceProvider {

    /**
     * logger
     */
    public static final Logger logger = Logger.getLogger("ServiceProvider");

    /**
     * 实现列表
     */
    private static final String IMPL_LIST = "implList";

    /**
     * 缺省实现类型
     */
    private static final String DEFAULT_TYPE = "defalutType";

    /**
     * service集合
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Map> serviceMap = null;

    /**
     * spring上下文
     */
    private static ApplicationContext springApplicationContext;

    /**
     * 从service-config中获取要实现的serviceId
     *
     * @param serviceId
     *            想要获取的serviceId
     * @return String 当前获取的实现impl
     */
    @SuppressWarnings("unchecked")
    public synchronized static void initialiaze(ApplicationContext applicationContext) throws DocumentException {
        springApplicationContext = applicationContext;
        serviceMap = new HashMap();
        SAXReader reader = new SAXReader();
        InputStream inStream = ServiceProvider.class.getResourceAsStream(
                "/topframework/service-config.xml");
        org.dom4j.Document doc = reader.read(inStream);
        Node root = doc.selectSingleNode("/service-config");
        String xpathServiceId = "service";
        List<Element> serviceList = root.selectNodes(xpathServiceId);
        //遍历所有的service
        for (Element serviceElement : serviceList) {
            Map map = new HashMap();
            String defaultType = serviceElement.attributeValue("default");
            String interfaceName = serviceElement.attributeValue("interface");
            List<InterfaceImpl> implList = new ArrayList<InterfaceImpl>();

            //查找默认类型的实现
            String xpathImplId = "impl";
            List<Element> implElementList = serviceElement.selectNodes(xpathImplId);
            for (Element implElement : implElementList) {
                String type = implElement.attributeValue("type");
                InterfaceImpl impl = new InterfaceImpl();
                impl.setType(InterfaceImplType.valueOf(InterfaceImplType.class, type));
                if ("ws".equals(type)) {
                    String address = implElement.attributeValue("address");
                    impl.setWsAddress(address);

                    String wstype = implElement.attributeValue("wstype");
                    impl.setWstype(wstype);
                }
                if ("local".equals(type)) {
                    String springBeanId = implElement.attributeValue("id");
                    impl.setSpringBeanId(springBeanId);
                }
				/*
				if ("ejb3".equals(type)) {
					String springBeanId = implElement.attributeValue("id");					
					impl.setSpringBeanId(springBeanId);					
					
					EJB3_Url = implElement.attributeValue("url");
					logger.info("------ initialiaze EJB3_Url:" + EJB3_Url);
				}	
				*/
                implList.add(impl);
            }

            map.put(DEFAULT_TYPE, defaultType);
            map.put(IMPL_LIST, implList);
            serviceMap.put(interfaceName, map);
        }
    }

    /**
     * 得到缺省的Service的实现
     *
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getService(Class<T> interfaceClass) throws DocumentException {
        Map map = serviceMap.get(interfaceClass.getName());
        if ("local".equals(map.get(DEFAULT_TYPE))) {
            return getLocalService(interfaceClass);
        }
        if ("ws".equals(map.get(DEFAULT_TYPE))) {
            return getWSService(interfaceClass);
        }
		/*
		if ("ejb3".equals(map.get(DEFAULT_TYPE))) {			
			return getEJB3Service(interfaceClass);
		}
		*/
        return null;
    }


    /**
     * 得到本地也就是spring的service的实现
     *
     * @param <T>
     * @param interfaceClass
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getLocalService(Class<T> interfaceClass) throws DocumentException {
        Map map = serviceMap.get(interfaceClass.getName());
        List<InterfaceImpl> implList = (List<InterfaceImpl>)map.get(IMPL_LIST);
        for(InterfaceImpl impl : implList){
            if(impl.getType() == InterfaceImplType.local){
                return (T) springApplicationContext.getBean(impl.getSpringBeanId());
            }
        }
        return null;
    }


    /**
     * 得到本地也就是EJB3的service的实现
     *
     * @param <T>
     * @param interfaceClass
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getEJB3Service(Class<T> interfaceClass)
            throws DocumentException {
        Map map = serviceMap.get(interfaceClass.getName());
        List<InterfaceImpl> implList = (List<InterfaceImpl>)map.get(IMPL_LIST);
        for(InterfaceImpl impl : implList){
            if(impl.getType() == InterfaceImplType.ejb3){
                logger.info("------ getEJB3Service SpringBeanId:"
                        + impl.getSpringBeanId());

                return (T) springApplicationContext.getBean(impl.getSpringBeanId());
            }
        }
        return null;
    }

    /**
     * 得到ws的service的实现
     *
     * @param <T>
     * @param interfaceClass
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getWSService(Class<T> interfaceClass) throws DocumentException {
        Map map = serviceMap.get(interfaceClass.getName());
        List<InterfaceImpl> implList = (List<InterfaceImpl>)map.get(IMPL_LIST);
        for(InterfaceImpl impl : implList){
            if(impl.getType() == InterfaceImplType.ws){
                //simple方式：即Simple Front-End, 支持POJO服务实现,发布是Simple Front-End方式
                if ("simple".equals(impl.getWstype())) {
//					ClientProxyFactoryBean  clientProxyFactoryBean = new ClientProxyFactoryBean ();
//					clientProxyFactoryBean
//							.setAddress(impl.getWsAddress());
//					clientProxyFactoryBean
//							.setServiceClass(interfaceClass);
//					if (clientProxyFactoryBean != null) {
//						return (T)clientProxyFactoryBean.create();
//					}
                }

                //jws方式，即JAX-WS Front-End支持采用JAX-WS API创建服务，即用注解来方式来创建，以JAX-WS方式发布的服务
                if ("jws".equals(impl.getWstype())) {
//					JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean ();
//					jaxWsProxyFactoryBean
//							.setAddress(impl.getWsAddress());
//					jaxWsProxyFactoryBean
//							.setServiceClass(interfaceClass);
//					if (jaxWsProxyFactoryBean != null) {
//						return (T)jaxWsProxyFactoryBean.create();
//					}
                }
            }
        }
        return null;
    }

    /**
     * 实现类
     *
     * @author cheney
     *
     */
    private static class InterfaceImpl {

        /**
         * 实现类型
         */
        private InterfaceImplType type  = null;

        /**
         * spring bean id
         */
        private String springBeanId = "";

        /**
         * web service的address
         */
        private String wsAddress = "";

        /**
         * web service类型
         */
        private String wstype = "";

        /**
         * url
         */
        private String url = "";

        /**
         * @return
         */
        public InterfaceImplType getType() {
            return type;
        }

        /**
         * @param type
         */
        public void setType(InterfaceImplType type) {
            this.type = type;
        }

        /**
         * @return
         */
        public String getSpringBeanId() {
            return springBeanId;
        }

        /**
         * @param springBeanId
         */
        public void setSpringBeanId(String springBeanId) {
            this.springBeanId = springBeanId;
        }

        /**
         * @return
         */
        public String getWsAddress() {
            return wsAddress;
        }

        /**
         * @param wsAddress
         */
        public void setWsAddress(String wsAddress) {
            this.wsAddress = wsAddress;
        }

        /**
         * @return
         */
        public String getWstype() {
            return wstype;
        }

        /**
         * @param wstype
         */
        public void setWstype(String wstype) {
            this.wstype = wstype;
        }

        /**
         * @return
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url
         */
        public void setUrl(String url) {
            this.url = url;
        }

    }

    /**
     * 实现类型
     *
     * @author cheney
     *
     */
    private enum InterfaceImplType {
        local,ws,ejb3
    }

    /**
     * 实现类型(Ws类型)
     *
     * @author wangjiaomiao
     *
     */
    @SuppressWarnings("unused")
    private enum WsImplType {
        jws,simple
    }



    /**
     * 根据完整的JNDI名称查找对象
     * @param jndipath
     * @return
     @SuppressWarnings("unchecked")
     public static Object getEJB(String jndipath) {
     try {
     Hashtable props = new Hashtable();
     props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
     "org.jnp.interfaces.NamingContextFactory");
     props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1199");
     InitialContext ctx = new InitialContext(props);
     return ctx.lookup(jndipath);
     } catch (NamingException e) {
     logger.error("=== getEJB error:" + e.getMessage());
     }
     return null;
     }
     */

    /**
     * 根据远程EJB名称查找EJB对象
     * @param beanName
     * @return

     @SuppressWarnings("unchecked")
     public static Object getRemoteEJB(String beanName) {
     try {
     Hashtable props = new Hashtable();
     props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
     "org.jnp.interfaces.NamingContextFactory");
     props.put(InitialContext.PROVIDER_URL, EJB3_Url);
     InitialContext ctx = new InitialContext(props);
     return ctx.lookup(beanName+"/remote");
     } catch (Exception e) {
     logger.error("=== getRemoteEJB error:" + e.getMessage());
     e.printStackTrace();
     }
     return null;
     }
     */

    /**
     * 根据本地EJB名称查找EJB对象
     * @param beanName
     * @return
     @SuppressWarnings("unchecked")
     public static Object getLocalEJB(String beanName) {
     try {
     Hashtable props = new Hashtable();
     props.put(InitialContext.INITIAL_CONTEXT_FACTORY,
     "org.jnp.interfaces.NamingContextFactory");
     props.put(InitialContext.PROVIDER_URL, "jnp://localhost:1199");
     InitialContext ctx = new InitialContext(props);
     return ctx.lookup(beanName+"/local");
     } catch (NamingException e) {
     logger.error("=== getLocalEJB error:" + e.getMessage());
     }
     return null;
     }
     */

    public static String getDBConfigFromFile(String configName) {
        String configValue = null;
        InputStream is = null;
        try {
            logger.info("--- begin getDBConfigFromFile ---");
            is = ServiceProvider.class.getResourceAsStream(
                    "/config/jdbc.properties");
            Properties property = new Properties();
            property.load(is);
            configValue = property.getProperty(configName).trim();
            logger.info("------ getDBConfigFromFile configValue:" + configValue);
        } catch (Exception e) {
            logger.error("=== getDBConfigFromFile error:" + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                logger.error("=== getDBConfigFromFile finally error:"
                        + e.getMessage());
            }
        }
        return configValue;
    }


    public static String convertStreamToString(InputStream is) {
    	/*
    	* 为了将InputStream转换成String我们使用函数BufferedReader.readLine().
    	* 我们迭代调用BufferedReader直到其返回null, null意味着没有其他的数据要读取了.
    	* 每一行将会追加到StringBuilder的末尾, StringBuilder将作为String返回。
    	*/
        StringBuilder sb = null;
        BufferedReader reader = null;
        if (is != null) {
            sb = new StringBuilder();
            String line;
            try {
                reader =
                        new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (Exception e) {
                logger.error("=== convertStreamToString error:"
                        + e.getMessage());
            } finally {
                try {
                    is.close();
                    reader.close();
                } catch (IOException e) {
                    logger.error("=== convertStreamToString finally error:"
                            + e.getMessage());
                }
            }
            return sb.toString();
        } else {
            logger.warn("--- convertStreamToString return null ---");
            return "";
        }
    }


    public static String inputStream2String(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return   baos.toString();
    }

}