package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetCheckinfoDao;
import com.wonders.asset.model.AssetCheckinfo;

import java.util.List;

/**
 * Created by Administrator on 2014/11/8.
 */
public class AssetCheckinfoDaoImpl extends BaseDaoImpl<AssetCheckinfo, String> implements AssetCheckinfoDao {

    @Override
    public List<Object[]> getAssetTaskByYear() {
        return null;
    }

    public AssetCheckinfoDaoImpl() {
        super(AssetCheckinfo.class);
    }

    public void batchUpdate() {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE T_ASSET_CHECKINFO D");
                sql.append("   SET (ASSET_NO,");
                sql.append("        CHECK_INFO,");
                sql.append("        CHECK_CODE,");
                sql.append("        CHECK_PERSON,");
                sql.append("        CHECK_DATE,");
                sql.append("        ASSET_TASK_ID) =");
                sql.append("       (SELECT ASSET_NO,");
                sql.append("               CHECK_INFO,");
                sql.append("               CHECK_CODE,");
                sql.append("               CHECK_PERSON,");
                sql.append("               CHECK_DATE,");
                sql.append("               ASSET_TASK_ID");
                sql.append("          FROM (SELECT *");
                sql.append("                  FROM (SELECT T.ASSET_NO,");
                sql.append("                               T.CHECK_INFO,");
                sql.append("                               T.CHECK_CODE,");
                sql.append("                               T.CHECK_PERSON,");
                sql.append("                               T.CHECK_DATE,");
                sql.append("                               X.ID ASSET_TASK_ID,");
                sql.append("                               ROW_NUMBER() OVER(PARTITION BY T.ASSET_NO ORDER BY UPLOAD_DATE DESC) AS NUM_");
                sql.append("                          FROM T_ASSET_CHECKINFO_TMP T, T_ASSET_TASK X");
                sql.append("                         WHERE X.EAMTASKID = T.ID) T");
                sql.append("                 WHERE NUM_ = 1) T");
                sql.append("         WHERE T.ASSET_NO = D.ASSET_NO)");
                sql.append(" WHERE EXISTS (SELECT 1 FROM T_ASSET_CHECKINFO_TMP T WHERE T.ASSET_NO = D.ASSET_NO) ");
        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void batchInsert() {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO T_ASSET_CHECKINFO D");
        sql.append("  (ASSET_CHECKINFO_ID,");
        sql.append("   ASSET_NO,");
        sql.append("   CHECK_INFO,");
        sql.append("   CHECK_CODE,");
        sql.append("   CHECK_PERSON,");
        sql.append("   CHECK_DATE,");
        sql.append("   ASSET_TASK_ID)");
        sql.append("  SELECT SYS_GUID(),");
        sql.append("         ASSET_NO,");
        sql.append("         CHECK_INFO,");
        sql.append("         CHECK_CODE,");
        sql.append("         CHECK_PERSON,");
        sql.append("         CHECK_DATE,");
        sql.append("         ASSET_TASK_ID");
        sql.append("    FROM (SELECT *");
        sql.append("            FROM (SELECT  T.ASSET_NO,");
        sql.append("                          T.CHECK_INFO,");
        sql.append("                          T.CHECK_CODE,");
        sql.append("                          T.CHECK_PERSON,");
        sql.append("                          T.CHECK_DATE,");
        sql.append("                          X.ID ASSET_TASK_ID,");
        sql.append("                         ROW_NUMBER() OVER(PARTITION BY T.ASSET_NO ORDER BY UPLOAD_DATE DESC) AS NUM_");
        sql.append("                    FROM T_ASSET_CHECKINFO_TMP T, T_ASSET_TASK X");
        sql.append("                   WHERE X.EAMTASKID = T.ID) T");
        sql.append("           WHERE NUM_ = 1");
        sql.append("             AND NOT EXISTS");
        sql.append("           (SELECT 1 FROM T_ASSET_CHECKINFO D WHERE D.ASSET_NO = T.ASSET_NO))");

        System.out.println(sql.toString());
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    
    
    @Override
    protected String getTableName(boolean isTem){
        return !isTem ? "T_ASSET_CHECKINFO D" : "T_ASSET_CHECKINFO_TMP T";
    }

	public static void main(String[] args) {
        AssetCheckinfoDaoImpl impl = new AssetCheckinfoDaoImpl();
        impl.batchInsert();
        impl.batchUpdate();
    }


}
