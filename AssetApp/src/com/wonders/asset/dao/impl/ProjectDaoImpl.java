package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.ProjectDao;
import com.wonders.asset.model.Project;

public class ProjectDaoImpl extends BaseDaoImpl<Project, String> implements ProjectDao{

	public ProjectDaoImpl() {
		super(Project.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Project findByProjectNo(String projectNo) {
		String hql ="from Project t where t.projectNo='"+projectNo+"' and t.removed='0'";
		List<Project> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1) return list.get(0);	
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByLineCodeId(String lineCodeId) {
		String hql = "from Project t where t.lineCodeId='"+lineCodeId+"' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByAssetId(String assetId) {
		String hql ="from Project t where t.asset.id='"+assetId+"' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Project> findByShortName(String shortName){
		String hql="from Project t where t.removed='0' and t.shortName='"+shortName+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void syncProject() {
		String sql = "INSERT INTO T_PROJECT TCS" +
                "  (" +
                "   id                         ," +
                "  approval_date               ," +
                "  city_allowance              ," +
                "  contract_person             ," +
                "  contract_phone              ," +
                "  create_date                 ," +
                "  dispatch_no                 ," +
                "  keyword                     ," +
                "  money_source_type           ," +
                "  operate_date                ," +
                "  primary_design_budget       ," +
                "  primary_design_date         ," +
                "  primary_design_money        ," +
                "  primary_design_no           ," +
                "  primary_design_remark       ," +
                "  professional_type           ," +
                "  project_adddept             ," +
                "  project_adddept_id          ," +
                "  project_addperson           ," +
                "  project_addperson_id        ," +
                "  project_addtime_date        ," +
                "  project_attach_id           ," +
                "  project_budget_all          ," +
                "  project_checkunit           ," +
                "  project_company             ," +
                "  project_company_id          ," +
                "  project_createunit          ," +
                "  project_destory_date        ," +
                "  project_endtime_plan_date   ," +
                "  project_estimate_all        ," +
                "  project_executeunit         ," +
                "  project_explain             ," +
                "  project_feasibility_budget  ," +
                "  project_feasibility_date    ," +
                "  project_feasibility_no      ," +
                "  project_feasibility_remark  ," +
                "  project_moneysource         ," +
                "  project_name                ," +
                "  project_no                  ," +
                "  project_starttime_plan_date ," +
                "  project_state               ," +
                "  project_type                ," +
                "  project_type2               ," +
                "  remark                      ," +
                "  removed                     ," +
                "  substitute_construction     ," +
                "  short_name                  ," +
                "  line_code_id                ," +
                "  overhaul_content            ," +
                "  completed_final_price       ," +
                "  input_operation_date        ," +
                "  invest_added                ," +
                "  reserve_estimation          ," +
                "  tax                         ," +
                "  spare_parts                 )" +
                "  SELECT  id                         ," +
                "  approval_date               ," +
                "  city_allowance              ," +
                "  contract_person             ," +
                "  contract_phone              ," +
                "  create_date                 ," +
                "  dispatch_no                 ," +
                "  keyword                     ," +
                "  money_source_type           ," +
                "  operate_date                ," +
                "  primary_design_budget       ," +
                "  primary_design_date         ," +
                "  primary_design_money        ," +
                "  primary_design_no           ," +
                "  primary_design_remark       ," +
                "  professional_type           ," +
                "  project_adddept             ," +
                "  project_adddept_id          ," +
                "  project_addperson           ," +
                "  project_addperson_id        ," +
                "  project_addtime_date        ," +
                "  project_attach_id           ," +
                "  project_budget_all          ," +
                "  project_checkunit           ," +
                "  project_company             ," +
                "  project_company_id          ," +
                "  project_createunit          ," +
                "  project_destory_date        ," +
                "  project_endtime_plan_date   ," +
                "  project_estimate_all        ," +
                "  project_executeunit         ," +
                "  project_explain             ," +
                "  project_feasibility_budget  ," +
                "  project_feasibility_date    ," +
                "  project_feasibility_no      ," +
                "  project_feasibility_remark  ," +
                "  project_moneysource         ," +
                "  project_name                ," +
                "  project_no                  ," +
                "  project_starttime_plan_date ," +
                "  project_state               ," +
                "  project_type                ," +
                "  project_type2               ," +
                "  remark                      ," +
                "  removed                     ," +
                "  substitute_construction     ," +
                "  ''                  ," +
                "  ''                ," +
                "  ''            ," +
                "  0       ," +
                "  SYSDATE        ," +
                "  0                ," +
                "  0          ," +
                "  0                         ," +
                "  0                 " +
                "    FROM V_PROJECT NCS" +
                "   WHERE NOT EXISTS" +
                "   (SELECT 1 FROM T_PROJECT TCS WHERE NCS.ID = TCS.ID) AND NCS.REMOVED='0' AND NCS.PROJECT_TYPE IN ('2','3','4','5')";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
		
		String sql2 = "UPDATE T_PROJECT TCS" +
                "   SET (id                         ," +
                "  approval_date               ," +
                "  city_allowance              ," +
                "  contract_person             ," +
                "  contract_phone              ," +
                "  create_date                 ," +
                "  dispatch_no                 ," +
                "  keyword                     ," +
                "  money_source_type           ," +
                "  operate_date                ," +
                "  primary_design_budget       ," +
                "  primary_design_date         ," +
                "  primary_design_money        ," +
                "  primary_design_no           ," +
                "  primary_design_remark       ," +
                "  professional_type           ," +
                "  project_adddept             ," +
                "  project_adddept_id          ," +
                "  project_addperson           ," +
                "  project_addperson_id        ," +
                "  project_addtime_date        ," +
                "  project_attach_id           ," +
                "  project_budget_all          ," +
                "  project_checkunit           ," +
                "  project_company             ," +
                "  project_company_id          ," +
                "  project_createunit          ," +
                "  project_destory_date        ," +
                "  project_endtime_plan_date   ," +
                "  project_estimate_all        ," +
                "  project_executeunit         ," +
                "  project_explain             ," +
                "  project_feasibility_budget  ," +
                "  project_feasibility_date    ," +
                "  project_feasibility_no      ," +
                "  project_feasibility_remark  ," +
                "  project_moneysource         ," +
                "  project_name                ," +
                "  project_no                  ," +
                "  project_starttime_plan_date ," +
                "  project_state               ," +
                "  project_type                ," +
                "  project_type2               ," +
                "  remark                      ," +
                "  removed                     ," +
                "  substitute_construction     ) =" +
                "       (SELECT id                         ," +
                "  approval_date               ," +
                "  city_allowance              ," +
                "  contract_person             ," +
                "  contract_phone              ," +
                "  create_date                 ," +
                "  dispatch_no                 ," +
                "  keyword                     ," +
                "  money_source_type           ," +
                "  operate_date                ," +
                "  primary_design_budget       ," +
                "  primary_design_date         ," +
                "  primary_design_money        ," +
                "  primary_design_no           ," +
                "  primary_design_remark       ," +
                "  professional_type           ," +
                "  project_adddept             ," +
                "  project_adddept_id          ," +
                "  project_addperson           ," +
                "  project_addperson_id        ," +
                "  project_addtime_date        ," +
                "  project_attach_id           ," +
                "  project_budget_all          ," +
                "  project_checkunit           ," +
                "  project_company             ," +
                "  project_company_id          ," +
                "  project_createunit          ," +
                "  project_destory_date        ," +
                "  project_endtime_plan_date   ," +
                "  project_estimate_all        ," +
                "  project_executeunit         ," +
                "  project_explain             ," +
                "  project_feasibility_budget  ," +
                "  project_feasibility_date    ," +
                "  project_feasibility_no      ," +
                "  project_feasibility_remark  ," +
                "  project_moneysource         ," +
                "  project_name                ," +
                "  project_no                  ," +
                "  project_starttime_plan_date ," +
                "  project_state               ," +
                "  project_type                ," +
                "  project_type2               ," +
                "  remark                      ," +
                "  removed                     ," +
                "  substitute_construction     " +
                "          FROM V_PROJECT NCS" +
                "         WHERE NCS.ID = TCS.ID" +
                "           AND NCS.OPERATE_DATE = TO_CHAR(SYSDATE - 1) AND NCS.PROJECT_TYPE IN ('2','3','4','5'))" +
                " WHERE EXISTS (SELECT 1" +
                "          FROM V_PROJECT NCS" +
                "         WHERE NCS.ID = TCS.ID" +
                "           AND NCS.OPERATE_DATE = TO_CHAR(SYSDATE - 1) AND NCS.PROJECT_TYPE IN ('2','3','4','5'))";
		Query query2 = getSession().createSQLQuery(sql2);
		query2.executeUpdate();
	}

	/**
	 * 查询小线
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,String>> findShortNameAndLineCode() {
		String sql = "SELECT SHORT_NAME,LINE_CODE_ID FROM T_PROJECT WHERE PROJECT_TYPE='1' AND SHORT_NAME IS NOT NULL AND LINE_CODE_ID IS NOT NULL ORDER BY TO_NUMBER(LINE_CODE_ID)";
		List<Object[]> list = getSession().createSQLQuery(sql).list();
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(Object[] obj : list){
			Map<String, String> map = new HashMap<String, String>();
			map.put("shortName", (String)obj[0]);
			if(((String)obj[1]).length() == 1){
				String tmp = "0" + (String)obj[1];
				map.put("lineCodeId", tmp);
			} else {
				map.put("lineCodeId", (String) obj[1]);
			}
			result.add(map);
		}
		return result;
	}  

	@Override
	public Double countAllInvest() {
		String sql = "select sum(project_budget_all) from T_PROJECT";
		BigDecimal res = (BigDecimal)getSession().createSQLQuery(sql).list().get(0);
		if(res == null){
			return new Double(0);
		}
		return res.doubleValue();
	}

	@Override
	public Double countInvestByYear(String year) {
		String sql = "select sum(project_budget_all) from T_PROJECT where  substr(approval_date,0,4)=?";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter(0, year);
		BigDecimal res = (BigDecimal) query.list().get(0);
		if(res == null){
			return new Double(0);
		}
		return res.doubleValue();
	}

}
