delete from t_project where project_type='99'
insert into t_project
  (id,dispatch_no, project_name, project_type)
  select sys_guid(),O.立项或可研批复文号, O.项目名称, '99'
    from (SELECT O.立项或可研批复文号, O.项目名称
            FROM asset_object_record2 O
           where O.竣工移交资产类型 IN ('新增', '更新')
             and not exists (select 1
                    from t_project
                   where O.立项或可研批复文号 = dispatch_no AND O.项目名称 = project_name)) o
   group by O.立项或可研批复文号, O.项目名称;
   
insert into t_enterprise
  (id, create_date, name, update_date, type)
  select sys_guid(), sysdate, name, sysdate, '2'
    from (select distinct o.生产厂商 name
            from asset_object_record2 o
           where not exists (select 1
                    from t_enterprise t
                   where o.生产厂商 = t.name
                     and t.type = '2') and o.生产厂商 is not null);

--导入供应商数据
insert into t_enterprise
  (id, create_date, name, update_date, type)
  select sys_guid(), sysdate, name, sysdate, '1'
    from (select distinct o.供应商 name
            from asset_object_record2 o
           where not exists (select 1
                    from t_enterprise t
                   where o.供应商 = t.name
                     and t.type = '1') and o.供应商 is not null);

--导入单位数据
insert into t_unit_master
  (id,  name)
  select sys_guid(), name
    from (select distinct o.单位 name
            from asset_object_record2 o
           where not exists (select 1
                    from t_unit_master t
                   where o.单位 = t.name) and o.单位 is not null);

INSERT INTO T_ASSET
  (ID,
   CREATE_DATE,
   REMARKS,
   UPDATE_DATE,
   ASSET_CODE,
   COUNT,
   EXPECTANCY_LIFE,
   MADE_DATE,
   MANUFACTURE_COUNTRY,
   NAME,
   TYPE,
   USE_DATE,
   USE_LIFE,
   WARRANTY_PERIOD,
   SUB_TYPE_CODE_ID,
   MANUFACTURER_ID,
   DETAIL_TYPE_CODE_ID,
   UNIT_ID,
   MAIN_TYPE_CODE_ID,
   CONTRACT_ID,
   PROJECT_ID,
   APPROVAL_SCRAP_DATE,
   ASSET_PIC,
   COMPLETE_TRANS_ASSET_TYPE,
   CONTRACT,
   DETAIL_INSTALL_SITE,
   OPERATE_PROJECT_ASSET,
   OPERATE_PROJECT_ASSET_DATE,
   OVERHAUL_RATE,
   PROJECT_APP_DOC_NO,
   PROJECT_NO,
   SUPPLIER_ID,
   VERIFY_STATE,
   DATA_LIST,
   EQUIPMENT_LIST,
   OVERHAUL_FINAL_PRICE,
   CONTRACT_PRICE,
   DEPRECIATION_METHOD,
   FACTORY_PRICE,
   ORIGINAL_VALUE,
   PROJECT_CONTRACT_NO,
   OWNERSHIP_PER,
   USE_PER,
   LOCATION_CODE,
   OPERATE_PROJECT_ASSET_ACCOUNT,ASSET_OWNER_INFO_ID,STATE_ID,PRICE_ID,
   ASSET_CODE_TYPE)
  SELECT SYS_GUID(),
         O.创建时间,
         O.备注,
         O.修改时间,
         O.资产编号,
         O.数量,
         O.预期使用寿命,
         O.出厂日期,
         O.产地,
         O.资产名称,
         O.规格型号,
         O.供应日期,
         O.设计使用年限,
         O.保修期至,
         (SELECT ID FROM T_ASSET_TYPE WHERE O.中类编码 = ALL_CODE),
         (SELECT ID
            FROM T_ENTERPRISE
           WHERE TYPE = '2'
             AND NAME = O.生产厂商),
         (SELECT ID FROM T_ASSET_TYPE WHERE O.资产类型 = ALL_CODE),
         (SELECT ID FROM T_UNIT_MASTER WHERE ID = O.单位),
         (SELECT ID FROM T_ASSET_TYPE WHERE O.大类编码 = ALL_CODE),
         (SELECT ID FROM T_CONTRACT WHERE O.项目合同编码 = CONTRACT_NO),
         (SELECT ID FROM T_PROJECT WHERE O.项目名称 = PROJECT_NAME AND O.立项或可研批复文号  = DISPATCH_NO) ,
         O.批准报废时间,
         O.资产图片名称,
         O.竣工移交资产类型,
         O.项目合同编码,
         O.安装地点,
         O.项目资产标示,
         to_date(O.资产交付日期,'yyyy-mm-dd'),
        O.大修频次,
         O.立项或可研批复文号,
         O.项目编号,
         (SELECT ID
            FROM T_ENTERPRISE
           WHERE TYPE = '1'
             AND NAME = O.供应商),
         '2',
         O.资料及清单,
         O.设备清单,
         '',
         O.合同价,
         O.折旧方法,
         O.出厂价,
         O.原值,
         O.项目合同编码,
         '',
         '',
         O.铭牌张贴位置,
         NULL,SYS_GUID(),SYS_GUID(),SYS_GUID(),
         '1'
     FROM asset_object_record2 O
    WHERE  not exists (select 1 from t_asset where asset_code = o.资产编号);
    
--导入资产权属
INSERT INTO T_ASSET_OWNER (ID,CREATE_DATE,UPDATE_DATE,REMARKS,GEGIN_USE_DATE,OWNER_,RECEIVE_DATE,STOP_USE_DATE,USER_,USER_ORG_CODE_ID,DEPARTMENT_CODE_ID,OWNER_ORG_CODE_ID,STATION_CODE_ID,
LINE_CODE_ID)
SELECT A.ASSET_OWNER_INFO_ID,A.CREATE_DATE,A.UPDATE_DATE,A.REMARKS,O.开始使用时间,O.权属负责人名称,O.移交时间,O.停止使用时间,O.使用负责人名称,
(SELECT ID FROM T_ORGANIZATION WHERE O.使用单位编号 = CODE),
(SELECT ID FROM T_DEPARTMENT WHERE O.维护部门编号 = CODE),
(SELECT ID FROM T_ORGANIZATION WHERE O.权属单位编号 = CODE),
(SELECT ID FROM T_STATION WHERE O.车站编码 = CODE AND O.线路编码 = LINE_ID),
(SELECT ID FROM T_LINE WHERE O.线路编码 = CODE)
FROM asset_object_record2 O,T_ASSET A
    WHERE  A.ASSET_CODE = O.资产编号 AND NOT EXISTS(SELECT 1 FROM T_ASSET_OWNER T WHERE T.ID = A.ASSET_OWNER_INFO_ID);

--导入资产状态
INSERT INTO T_ASSET_STATE
  (ID, CREATE_DATE, REMARKS, UPDATE_DATE, ASSET_ID, STATE, USE_STATE,NAMEPLATE_SITE) 
  SELECT A.STATE_ID,A.CREATE_DATE,A.REMARKS,A.UPDATE_DATE,A.ID,DECODE(O.当前使用状态,'使用','1','停用','2','报废','3','待报废','4','封存','5'),O.当前使用状态,
O.铭牌张贴位置
  FROM ASSET_OBJECT O, T_ASSET A 
   WHERE  A.ASSET_CODE = O.资产编号 AND NOT EXISTS(SELECT 1 FROM T_ASSET_STATE T WHERE T.ASSET_ID = A.ID);

--导入资产价值
INSERT INTO T_ASSET_PRICE
 (ID, CREATE_DATE, REMARKS, UPDATE_DATE, ACCUMULATED_DEPRECIATION, CONTRACT_PRICE, FACTORY_PRICE,
  NET_VALUE,ORIGINAL_VALUE,DEPRECIATION_METHOD,ASSET_ID) 
  SELECT A.PRICE_ID,A.CREATE_DATE,A.REMARKS,A.UPDATE_DATE,O.累计折旧,O.合同价,O.出厂价,
  O.资产净值,O.原值,O.折旧方法,A.ID

  FROM ASSET_OBJECT O, T_ASSET A 
     WHERE  A.ASSET_CODE = O.资产编号 AND NOT EXISTS(SELECT 1 FROM T_ASSET_PRICE T WHERE T.ASSET_ID = A.ID);
     
 --大修更新改造，初始-大修
update t_asset t set  t.asset_code_type = '2'  where exists (select 1 from asset_object_record2 o where o.资产编号 = t.asset_code and o.竣工移交资产类型 = '初始'
     and o.项目资产标示 = '大修' )  ;
update t_asset t set  t.asset_code_type = '1'  where exists (select 1 from asset_object_record2 o where o.资产编号 = t.asset_code and o.竣工移交资产类型 = '新增'
     and o.项目资产标示 = '改造' )  ;
update t_asset t set  t.asset_code_type = '2'  where exists (select 1 from asset_object_record2 o where o.资产编号 = t.asset_code and o.竣工移交资产类型 = '新增'
     and o.项目资产标示 = '大修' )  ;    
 
--导入资产履历
INSERT INTO t_Asset_Record
  (ID, PROJECT_NAME,  ASSET_TYPE, PROJECT_APP_NO, CREATE_DATE,UPDATE_DATE,ASSET_ID,FINISH_DATE,FINISH_PRICE) 
  SELECT SYS_GUID(),O.项目名称,O.项目资产标示,O.立项或可研批复文号,A.CREATE_DATE,A.UPDATE_DATE,A.ID,TO_DATE(O.资产交付日期,'yyyy/mm/dd'),O.资产决算价

  FROM asset_object_record2 O, T_ASSET A WHERE A.ASSET_CODE = O.资产编号 AND o.竣工移交资产类型 IN ('初始','新增') AND o.项目资产标示 IN ('大修','改造') ;


DELETE T_ASSET_OWNER T  WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ASSET_OWNER_INFO_ID = T.ID );
DELETE T_ASSET_PRICE T WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ID = T.ASSET_ID );
DELETE T_ASSET_STATE T WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ID = T.ASSET_ID );
DELETE T_ASSET T WHERE EXISTS (SELECT 1 FROM asset_object_record2 WHERE 资产编号 = t.Asset_Code);
DELETE t_Asset_Record
