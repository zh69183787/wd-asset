package com.wonders.framework.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	
	private static final SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			Resources.setCharset(Charset.forName("utf-8"));
			Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml"); 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private MyBatisUtils() {
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}

}
