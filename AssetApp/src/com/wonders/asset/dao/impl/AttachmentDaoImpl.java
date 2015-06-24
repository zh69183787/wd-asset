package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AttachmentDao;
import com.wonders.asset.model.Attachment;

public class AttachmentDaoImpl extends BaseDaoImpl<Attachment, String>
		implements AttachmentDao {
	public AttachmentDaoImpl() {
		super(Attachment.class);
	}

	public void batchUpdate() {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_ATTACHMENT D");
		sql.append("   SET (FILE_TITLE, FILE_TYPE, FILE_ROUTE, CREATE_TIME, CREATOR,TYPE,OBJECT_ID) = ");
		sql.append("       (SELECT FILE_TITLE, FILE_TYPE, FILE_ROUTE, CREATE_TIME, CREATOR,TYPE,OBJECT_ID");
		sql.append("         FROM (SELECT *");
		sql.append("                 FROM (SELECT T.*,");
		sql.append("                             ROW_NUMBER() OVER(PARTITION BY ATTACHMENT_ID ORDER BY UPLOAD_DATE DESC) AS NUM_");
		sql.append("                          FROM T_ATTACHMENT_TMP T) T");
		sql.append("                 WHERE NUM_ = 1) T");
		sql.append("         WHERE T.ATTACHMENT_ID = D.ID)");
		sql.append(" 	 WHERE EXISTS (SELECT 1 FROM T_ATTACHMENT_TMP T WHERE T.ATTACHMENT_ID = D.ID)");
		System.out.println(sql.toString());
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	public void batchInsert() {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO T_ATTACHMENT D");
		sql.append(" ( ");
		sql.append(" ATTACHMENT_ID,");
		sql.append("FILE_TITLE,");
		sql.append("FILE_TYPE,");
		sql.append("FILE_ROUTE,");
		sql.append(" CREATE_TIME,");
		sql.append("  CREATOR,");
		sql.append(" TYPE,");
		sql.append(" ID,OBJECT_ID)");
		sql.append("  SELECT SYS_GUID(),");
		sql.append("FILE_TITLE,");
		sql.append(" FILE_TYPE,");
		sql.append(" FILE_ROUTE,");
		sql.append("CREATE_TIME,");
		sql.append("CREATOR,");
		sql.append("TYPE,");
		sql.append(" ATTACHMENT_ID,OBJECT_ID");
		sql.append(" FROM (SELECT *");
		sql.append(" FROM (SELECT T.*,");
		sql.append("ROW_NUMBER() OVER(PARTITION BY ATTACHMENT_ID ORDER BY UPLOAD_DATE DESC) AS NUM_");
		sql.append(" FROM T_ATTACHMENT_TMP T) T");
		sql.append(" WHERE NUM_ = 1");
		sql.append(" AND NOT EXISTS");
		sql.append("(SELECT 1 FROM T_ATTACHMENT D WHERE D.ID = T.ATTACHMENT_ID))");

		System.out.println(sql.toString());
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}
	
	public void updateObjectId(String objectId,String type) {  
		StringBuffer sql = new StringBuffer();
		String tableName = "";
		String pk = "";
		if("1".equals(type)){
			tableName="T_DAMAGE_ASSET";  
			pk = "DAMAGE_ASSET_ID";
		}
		if("2".equals(type)){
			tableName="T_ALLOCATE_ASSET";
			pk = "ALLOCATE_ASSET_ID";
		}
		if("3".equals(type)){
			tableName="T_BORROW_ASSET";
			pk = "BORROW_ASSET_ID";
		}
		if("4".equals(type)){
			tableName="T_STOP_ASSET";
			pk = "STOP_ASSET_ID";
		}
		if("5".equals(type)){
			tableName="T_DISABLE_ASSET";
			pk = "DISABLE_ASSET_ID";
		}
	    sql.append("UPDATE T_ATTACHMENT D SET OBJECT_ID=(SELECT ").append(pk).append(" FROM ").append(tableName).append(" WHERE ID='").append(objectId).append("') WHERE OBJECT_ID='").append(objectId).append("'");
	    System.out.println(sql.toString());
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	protected String getTableName(boolean isTem) {
		return !isTem ? "T_ATTACHMENT D" : "T_ATTACHMENT_TMP T";
	}

	public static void main(String args[]) {
		AttachmentDaoImpl t = new AttachmentDaoImpl();
		t.batchInsert();
	}

	@Override
	public List<Attachment> findByAttachmentId(String objectId,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("from Attachment where objectId = :objectId and type =:type");
		
		System.out.println(sql.toString());
		Query query = getSession().createQuery(sql.toString()).setString("objectId", objectId).setString("type",type);
		return query.list();
	}
}
