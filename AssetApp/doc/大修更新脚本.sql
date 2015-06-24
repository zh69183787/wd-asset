delete from t_project where project_type='99'
insert into t_project
  (id,dispatch_no, project_name, project_type)
  select sys_guid(),O.�������������ĺ�, O.��Ŀ����, '99'
    from (SELECT O.�������������ĺ�, O.��Ŀ����
            FROM asset_object_record2 O
           where O.�����ƽ��ʲ����� IN ('����', '����')
             and not exists (select 1
                    from t_project
                   where O.�������������ĺ� = dispatch_no AND O.��Ŀ���� = project_name)) o
   group by O.�������������ĺ�, O.��Ŀ����;
   
insert into t_enterprise
  (id, create_date, name, update_date, type)
  select sys_guid(), sysdate, name, sysdate, '2'
    from (select distinct o.�������� name
            from asset_object_record2 o
           where not exists (select 1
                    from t_enterprise t
                   where o.�������� = t.name
                     and t.type = '2') and o.�������� is not null);

--���빩Ӧ������
insert into t_enterprise
  (id, create_date, name, update_date, type)
  select sys_guid(), sysdate, name, sysdate, '1'
    from (select distinct o.��Ӧ�� name
            from asset_object_record2 o
           where not exists (select 1
                    from t_enterprise t
                   where o.��Ӧ�� = t.name
                     and t.type = '1') and o.��Ӧ�� is not null);

--���뵥λ����
insert into t_unit_master
  (id,  name)
  select sys_guid(), name
    from (select distinct o.��λ name
            from asset_object_record2 o
           where not exists (select 1
                    from t_unit_master t
                   where o.��λ = t.name) and o.��λ is not null);

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
         O.����ʱ��,
         O.��ע,
         O.�޸�ʱ��,
         O.�ʲ����,
         O.����,
         O.Ԥ��ʹ������,
         O.��������,
         O.����,
         O.�ʲ�����,
         O.����ͺ�,
         O.��Ӧ����,
         O.���ʹ������,
         O.��������,
         (SELECT ID FROM T_ASSET_TYPE WHERE O.������� = ALL_CODE),
         (SELECT ID
            FROM T_ENTERPRISE
           WHERE TYPE = '2'
             AND NAME = O.��������),
         (SELECT ID FROM T_ASSET_TYPE WHERE O.�ʲ����� = ALL_CODE),
         (SELECT ID FROM T_UNIT_MASTER WHERE ID = O.��λ),
         (SELECT ID FROM T_ASSET_TYPE WHERE O.������� = ALL_CODE),
         (SELECT ID FROM T_CONTRACT WHERE O.��Ŀ��ͬ���� = CONTRACT_NO),
         (SELECT ID FROM T_PROJECT WHERE O.��Ŀ���� = PROJECT_NAME AND O.�������������ĺ�  = DISPATCH_NO) ,
         O.��׼����ʱ��,
         O.�ʲ�ͼƬ����,
         O.�����ƽ��ʲ�����,
         O.��Ŀ��ͬ����,
         O.��װ�ص�,
         O.��Ŀ�ʲ���ʾ,
         to_date(O.�ʲ���������,'yyyy-mm-dd'),
        O.����Ƶ��,
         O.�������������ĺ�,
         O.��Ŀ���,
         (SELECT ID
            FROM T_ENTERPRISE
           WHERE TYPE = '1'
             AND NAME = O.��Ӧ��),
         '2',
         O.���ϼ��嵥,
         O.�豸�嵥,
         '',
         O.��ͬ��,
         O.�۾ɷ���,
         O.������,
         O.ԭֵ,
         O.��Ŀ��ͬ����,
         '',
         '',
         O.��������λ��,
         NULL,SYS_GUID(),SYS_GUID(),SYS_GUID(),
         '1'
     FROM asset_object_record2 O
    WHERE  not exists (select 1 from t_asset where asset_code = o.�ʲ����);
    
--�����ʲ�Ȩ��
INSERT INTO T_ASSET_OWNER (ID,CREATE_DATE,UPDATE_DATE,REMARKS,GEGIN_USE_DATE,OWNER_,RECEIVE_DATE,STOP_USE_DATE,USER_,USER_ORG_CODE_ID,DEPARTMENT_CODE_ID,OWNER_ORG_CODE_ID,STATION_CODE_ID,
LINE_CODE_ID)
SELECT A.ASSET_OWNER_INFO_ID,A.CREATE_DATE,A.UPDATE_DATE,A.REMARKS,O.��ʼʹ��ʱ��,O.Ȩ������������,O.�ƽ�ʱ��,O.ֹͣʹ��ʱ��,O.ʹ�ø���������,
(SELECT ID FROM T_ORGANIZATION WHERE O.ʹ�õ�λ��� = CODE),
(SELECT ID FROM T_DEPARTMENT WHERE O.ά�����ű�� = CODE),
(SELECT ID FROM T_ORGANIZATION WHERE O.Ȩ����λ��� = CODE),
(SELECT ID FROM T_STATION WHERE O.��վ���� = CODE AND O.��·���� = LINE_ID),
(SELECT ID FROM T_LINE WHERE O.��·���� = CODE)
FROM asset_object_record2 O,T_ASSET A
    WHERE  A.ASSET_CODE = O.�ʲ���� AND NOT EXISTS(SELECT 1 FROM T_ASSET_OWNER T WHERE T.ID = A.ASSET_OWNER_INFO_ID);

--�����ʲ�״̬
INSERT INTO T_ASSET_STATE
  (ID, CREATE_DATE, REMARKS, UPDATE_DATE, ASSET_ID, STATE, USE_STATE,NAMEPLATE_SITE) 
  SELECT A.STATE_ID,A.CREATE_DATE,A.REMARKS,A.UPDATE_DATE,A.ID,DECODE(O.��ǰʹ��״̬,'ʹ��','1','ͣ��','2','����','3','������','4','���','5'),O.��ǰʹ��״̬,
O.��������λ��
  FROM ASSET_OBJECT O, T_ASSET A 
   WHERE  A.ASSET_CODE = O.�ʲ���� AND NOT EXISTS(SELECT 1 FROM T_ASSET_STATE T WHERE T.ASSET_ID = A.ID);

--�����ʲ���ֵ
INSERT INTO T_ASSET_PRICE
 (ID, CREATE_DATE, REMARKS, UPDATE_DATE, ACCUMULATED_DEPRECIATION, CONTRACT_PRICE, FACTORY_PRICE,
  NET_VALUE,ORIGINAL_VALUE,DEPRECIATION_METHOD,ASSET_ID) 
  SELECT A.PRICE_ID,A.CREATE_DATE,A.REMARKS,A.UPDATE_DATE,O.�ۼ��۾�,O.��ͬ��,O.������,
  O.�ʲ���ֵ,O.ԭֵ,O.�۾ɷ���,A.ID

  FROM ASSET_OBJECT O, T_ASSET A 
     WHERE  A.ASSET_CODE = O.�ʲ���� AND NOT EXISTS(SELECT 1 FROM T_ASSET_PRICE T WHERE T.ASSET_ID = A.ID);
     
 --���޸��¸��죬��ʼ-����
update t_asset t set  t.asset_code_type = '2'  where exists (select 1 from asset_object_record2 o where o.�ʲ���� = t.asset_code and o.�����ƽ��ʲ����� = '��ʼ'
     and o.��Ŀ�ʲ���ʾ = '����' )  ;
update t_asset t set  t.asset_code_type = '1'  where exists (select 1 from asset_object_record2 o where o.�ʲ���� = t.asset_code and o.�����ƽ��ʲ����� = '����'
     and o.��Ŀ�ʲ���ʾ = '����' )  ;
update t_asset t set  t.asset_code_type = '2'  where exists (select 1 from asset_object_record2 o where o.�ʲ���� = t.asset_code and o.�����ƽ��ʲ����� = '����'
     and o.��Ŀ�ʲ���ʾ = '����' )  ;    
 
--�����ʲ�����
INSERT INTO t_Asset_Record
  (ID, PROJECT_NAME,  ASSET_TYPE, PROJECT_APP_NO, CREATE_DATE,UPDATE_DATE,ASSET_ID,FINISH_DATE,FINISH_PRICE) 
  SELECT SYS_GUID(),O.��Ŀ����,O.��Ŀ�ʲ���ʾ,O.�������������ĺ�,A.CREATE_DATE,A.UPDATE_DATE,A.ID,TO_DATE(O.�ʲ���������,'yyyy/mm/dd'),O.�ʲ������

  FROM asset_object_record2 O, T_ASSET A WHERE A.ASSET_CODE = O.�ʲ���� AND o.�����ƽ��ʲ����� IN ('��ʼ','����') AND o.��Ŀ�ʲ���ʾ IN ('����','����') ;


DELETE T_ASSET_OWNER T  WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ASSET_OWNER_INFO_ID = T.ID );
DELETE T_ASSET_PRICE T WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ID = T.ASSET_ID );
DELETE T_ASSET_STATE T WHERE NOT EXISTS (SELECT 1 FROM T_ASSET A WHERE A.ID = T.ASSET_ID );
DELETE T_ASSET T WHERE EXISTS (SELECT 1 FROM asset_object_record2 WHERE �ʲ���� = t.Asset_Code);
DELETE t_Asset_Record
