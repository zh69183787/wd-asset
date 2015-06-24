package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.ContractDao;
import com.wonders.asset.model.Contract;

public class ContractDaoImpl extends BaseDaoImpl<Contract, String> implements ContractDao{

	public ContractDaoImpl() {
		super(Contract.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Contract findByContractNo(String contractNo) {
		String hql ="from Contract t where t.contractNo='"+contractNo+"' and t.removed='0'";
		List<Contract> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0) return list.get(0);		//坑，理论上contractNo不重复
		return null;
	}

	@Override
	public void syncContract() {


        String sql3 = "INSERT INTO T_CONTRACT TC" +
                "  (id," +
                "   build_supplier_id," +
                "   build_supplier_name," +
                "   contract_attachment," +
                "   contract_content," +
                "   contract_destory_date," +
                "   contract_end_date," +
                "   contract_grouping," +
                "   contract_name," +
                "   contract_no," +
                "   contract_owner_execute_id," +
                "   contract_owner_execute_name," +
                "   contract_owner_id," +
                "   contract_owner_name," +
                "   contract_passed_date," +
                "   contract_plan_id," +
                "   contract_plan_no," +
                "   contract_price," +
                "   contract_signed_date," +
                "   contract_start_date," +
                "   contract_status," +
                "   contract_type," +
                "   create_date," +
                "   create_type," +
                "   final_price," +
                "   invite_bid_type," +
                "   nation_verify_price," +
                "   pay_type," +
                "   project_id," +
                "   project_name," +
                "   project_no," +
                "   register_person_loginname," +
                "   register_person_name," +
                "   remark," +
                "   removed," +
                "   self_no," +
                "   update_date," +
                "   verify_price)" +
                "  SELECT id," +
                "         build_supplier_id," +
                "         build_supplier_name," +
                "         contract_attachment," +
                "         contract_content," +
                "         contract_destory_date," +
                "         contract_end_date," +
                "         contract_grouping," +
                "         contract_name," +
                "         contract_no," +
                "         contract_owner_execute_id," +
                "         contract_owner_execute_name," +
                "         contract_owner_id," +
                "         contract_owner_name," +
                "         contract_passed_date," +
                "         contract_plan_id," +
                "         contract_plan_no," +
                "         contract_price," +
                "         contract_signed_date," +
                "         contract_start_date," +
                "         contract_status," +
                "         contract_type," +
                "         create_date," +
                "         create_type," +
                "         final_price," +
                "         invite_bid_type," +
                "         nation_verify_price," +
                "         pay_type," +
                "         project_id," +
                "         project_name," +
                "         project_no," +
                "         register_person_loginname," +
                "         register_person_name," +
                "         remark," +
                "         removed," +
                "         self_no," +
                "         update_date," +
                "         verify_price" +
                "    FROM V_CONTRACT NC" +
                "   WHERE NOT EXISTS (SELECT 1 FROM T_CONTRACT TC WHERE NC.ID = TC.ID)";
        Query query3 = getSession().createSQLQuery(sql3);
        query3.executeUpdate();

        String sql4 = "UPDATE T_CONTRACT TC" +
                "   SET (id," +
                "        build_supplier_id," +
                "        build_supplier_name," +
                "        contract_attachment," +
                "        contract_content," +
                "        contract_destory_date," +
                "        contract_end_date," +
                "        contract_grouping," +
                "        contract_name," +
                "        contract_no," +
                "        contract_owner_execute_id," +
                "        contract_owner_execute_name," +
                "        contract_owner_id," +
                "        contract_owner_name," +
                "        contract_passed_date," +
                "        contract_plan_id," +
                "        contract_plan_no," +
                "        contract_price," +
                "        contract_signed_date," +
                "        contract_start_date," +
                "        contract_status," +
                "        contract_type," +
                "        create_date," +
                "        create_type," +
                "        final_price," +
                "        invite_bid_type," +
                "        nation_verify_price," +
                "        pay_type," +
                "        project_id," +
                "        project_name," +
                "        project_no," +
                "        register_person_loginname," +
                "        register_person_name," +
                "        remark," +
                "        removed," +
                "        self_no," +
                "        update_date," +
                "        verify_price) =" +
                "       (SELECT id," +
                "               build_supplier_id," +
                "               build_supplier_name," +
                "               contract_attachment," +
                "               contract_content," +
                "               contract_destory_date," +
                "               contract_end_date," +
                "               contract_grouping," +
                "               contract_name," +
                "               contract_no," +
                "               contract_owner_execute_id," +
                "               contract_owner_execute_name," +
                "               contract_owner_id," +
                "               contract_owner_name," +
                "               contract_passed_date," +
                "               contract_plan_id," +
                "               contract_plan_no," +
                "               contract_price," +
                "               contract_signed_date," +
                "               contract_start_date," +
                "               contract_status," +
                "               contract_type," +
                "               create_date," +
                "               create_type," +
                "               final_price," +
                "               invite_bid_type," +
                "               nation_verify_price," +
                "               pay_type," +
                "               project_id," +
                "               project_name," +
                "               project_no," +
                "               register_person_loginname," +
                "               register_person_name," +
                "               remark," +
                "               removed," +
                "               self_no," +
                "               update_date," +
                "               verify_price" +
                "          FROM V_CONTRACT NC" +
                "         WHERE NC.ID = TC.ID" +
                "           AND NC.UPDATE_DATE = TO_CHAR(SYSDATE - 1))" +
                " WHERE EXISTS (SELECT 1" +
                "          FROM V_CONTRACT NC" +
                "         WHERE NC.ID = TC.ID" +
                "           AND NC.UPDATE_DATE = TO_CHAR(SYSDATE - 1))";
        Query query4 = getSession().createSQLQuery(sql4);
        query4.executeUpdate();

		String sql = "INSERT INTO C_CONTRACT_STATUS TCS" +
                "  (id," +
                "   contract_id," +
                "   reason," +
                "   money," +
                "   persent," +
                "   operate_date," +
                "   remark," +
                "   removed," +
                "   type," +
                "   update_date," +
                "   change_flow_no," +
                "   change_raised_company," +
                "   attach," +
                "   line)" +
                "  SELECT id," +
                "         contract_id," +
                "         reason," +
                "         money," +
                "         persent," +
                "         operate_date," +
                "         remark," +
                "         removed," +
                "         type," +
                "         update_date," +
                "         change_flow_no," +
                "         change_raised_company," +
                "         attach," +
                "         line" +
                "    FROM V_CONTRACT_STATUS NCS" +
                "   WHERE NOT EXISTS" +
                "   (SELECT 1 FROM C_CONTRACT_STATUS TCS WHERE NCS.ID = TCS.ID)";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
		
		String sql2 = "UPDATE C_CONTRACT_STATUS TCS" +
                "   SET (id," +
                "        contract_id," +
                "        reason," +
                "        money," +
                "        persent," +
                "        operate_date," +
                "        remark," +
                "        removed," +
                "        type," +
                "        update_date," +
                "        change_flow_no," +
                "        change_raised_company," +
                "        attach," +
                "        line) =" +
                "       (SELECT id," +
                "               contract_id," +
                "               reason," +
                "               money," +
                "               persent," +
                "               operate_date," +
                "               remark," +
                "               removed," +
                "               type," +
                "               update_date," +
                "               change_flow_no," +
                "               change_raised_company," +
                "               attach," +
                "               line" +
                "          FROM V_CONTRACT_STATUS NCS" +
                "         WHERE NCS.ID = TCS.ID" +
                "           AND NCS.UPDATE_DATE = TO_CHAR(SYSDATE - 1))" +
                " WHERE EXISTS (SELECT 1" +
                "          FROM V_CONTRACT_STATUS NCS" +
                "         WHERE NCS.ID = TCS.ID" +
                "           AND NCS.UPDATE_DATE = TO_CHAR(SYSDATE - 1))";
		Query query2 = getSession().createSQLQuery(sql2);
		query2.executeUpdate();

	}
	
	
}
