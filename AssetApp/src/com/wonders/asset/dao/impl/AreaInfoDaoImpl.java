package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AreaInfoDao;
import com.wonders.asset.model.AreaInfo;

public class AreaInfoDaoImpl extends BaseDaoImpl<AreaInfo, String> implements AreaInfoDao{

	public AreaInfoDaoImpl() {
		super(AreaInfo.class);
	}
	
	protected String getTableName(boolean isTem){
        return !isTem ? "T_AREA_INFO D" : "T_AREA_INFO_TMP T";
    }
	
	/**
	 * 从临时表批量添加数据到正式表
	 */
	public void batchInsert() {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO T_AREA_INFO D");
        sql.append("	(AREA_INFO_ID,");
        sql.append("	ASSET_NO,");
        sql.append("	TAKE_OVER_DEP,");
        sql.append("	USE_TYPE,");
        sql.append("	REALLY_AREA,");
        sql.append("    IN_FLOOR,");
        sql.append(" 	NOTE,");
    	sql.append(" 	ID,");
        sql.append(" 	HOUSE_ASSET_ID,DETAIL)");
        sql.append("   SELECT SYS_GUID(),");
        sql.append("       ASSET_NO,");
        sql.append("       TAKE_OVER_DEP,");
        sql.append("       USE_TYPE,");
        sql.append("       REALLY_AREA,");
        sql.append("       IN_FLOOR,");
        sql.append("       NOTE,");
        sql.append("       ID,");
        sql.append("      HOUSE_ASSET_ID,DETAIL");
        sql.append("  FROM (SELECT *");
        sql.append("         FROM (SELECT  T.ASSET_NO,");
        sql.append("                       T.TAKE_OVER_DEP,");
        sql.append("                       T.USE_TYPE,");
        sql.append("                       T.REALLY_AREA,");
        sql.append("                       T.IN_FLOOR,");
        sql.append("                       T.NOTE,");
        sql.append("                       T.ID,");
       	sql.append("                       X.HOUSE_ASSET_ID,DETAIL,");
       	sql.append("                       ROW_NUMBER() OVER(PARTITION BY T.ID ORDER BY UPLOAD_DATE DESC) AS NUM_");
        sql.append("                  FROM T_AREA_INFO_TMP T, T_HOUSE_ASSET X");
        sql.append("                 WHERE X.ID = T.HOUSE_ASSET_ID) T");
        sql.append("        WHERE NUM_ = 1");
        sql.append("          AND NOT EXISTS");
        sql.append("         (SELECT 1 FROM T_AREA_INFO D WHERE D.ID = T.ID))");

        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }

	/**
	 * 从临时表批量更新数据到正式表
	 */
	public void batchUpdate() {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE T_AREA_INFO D ");
        sql.append("SET (ASSET_NO,");
        sql.append("     TAKE_OVER_DEP,");
        sql.append("     USE_TYPE,");
        sql.append("     REALLY_AREA,");
        sql.append("     IN_FLOOR,");
        sql.append("     NOTE,");
        sql.append("     HOUSE_ASSET_ID,DETAIL) =");
        sql.append("    (SELECT ASSET_NO,");
        sql.append("            TAKE_OVER_DEP,");
        sql.append("            USE_TYPE,");
        sql.append("            REALLY_AREA,");
        sql.append("            IN_FLOOR,");
        sql.append("            NOTE,");
        sql.append("            HOUSE_ASSET_ID,DETAIL");
        sql.append("       FROM (SELECT *");
        sql.append("               FROM (SELECT T.ASSET_NO,");
        sql.append("                            T.TAKE_OVER_DEP,");
        sql.append("                            T.USE_TYPE,");
        sql.append("                            T.REALLY_AREA,");
      	sql.append("                            T.IN_FLOOR,");
        sql.append("                            T.NOTE,");
        sql.append("                            X.HOUSE_ASSET_ID,DETAIL,");
        sql.append("                            ROW_NUMBER() OVER(PARTITION BY T.ID ORDER BY UPLOAD_DATE DESC) AS NUM_,T.ID");
        sql.append("                       FROM T_AREA_INFO_TMP T, T_HOUSE_ASSET X");
        sql.append("                      WHERE X.ID = T.HOUSE_ASSET_ID) T");
    	sql.append("              WHERE NUM_ = 1) T");
       	sql.append("      WHERE T.ID = D.ID)");
        sql.append("WHERE EXISTS (SELECT 1 FROM T_AREA_INFO_TMP T WHERE T.ID = D.ID)");
        
        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}
