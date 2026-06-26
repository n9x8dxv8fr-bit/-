-- Quality inspection standard table and menu
-- Skip any statement if table/menu already exists

CREATE TABLE IF NOT EXISTS quality_inspection_standard (
  id                  BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
  standard_name       VARCHAR(128) NULL     COMMENT 'standard name',
  size_precision      VARCHAR(256) NULL     COMMENT 'size and precision',
  surface_roughness   VARCHAR(128) NULL     COMMENT 'surface roughness',
  hardness            VARCHAR(128) NULL     COMMENT 'hardness',
  material            VARCHAR(128) NULL     COMMENT 'material',
  status              CHAR(1)      DEFAULT '0' COMMENT '0=normal 1=disabled',
  remark              VARCHAR(512) NULL     COMMENT 'remark',
  create_by           VARCHAR(64)  DEFAULT '' COMMENT 'create by',
  create_time         DATETIME     NULL     COMMENT 'create time',
  update_by           VARCHAR(64)  DEFAULT '' COMMENT 'update by',
  update_time         DATETIME     NULL     COMMENT 'update time',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='quality inspection standard';

-- Menu IDs 2100-2105; change IDs if conflict
DELETE FROM sys_role_menu WHERE menu_id IN (2100, 2101, 2102, 2103, 2104, 2105);
DELETE FROM sys_menu WHERE menu_id IN (2100, 2101, 2102, 2103, 2104, 2105);

INSERT INTO sys_menu VALUES(2100, '�ʼ��׼', 0, 10, 'qcStandard', 'system/qcStandard/index', '', '', 1, 0, 'C', '0', '0', 'system:qcStandard:list', 'documentation', 'admin', sysdate(), '', null, '�ʼ��׼�˵�');
INSERT INTO sys_menu VALUES(2101, '�ʼ��׼��ѯ', 2100, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu VALUES(2102, '�ʼ��׼����', 2100, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu VALUES(2103, '�ʼ��׼�޸�', 2100, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu VALUES(2104, '�ʼ��׼ɾ��', 2100, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO sys_menu VALUES(2105, '�ʼ��׼����', 2100, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:export', '#', 'admin', sysdate(), '', null, '');

INSERT INTO sys_role_menu VALUES (1, 2100);
INSERT INTO sys_role_menu VALUES (1, 2101);
INSERT INTO sys_role_menu VALUES (1, 2102);
INSERT INTO sys_role_menu VALUES (1, 2103);
INSERT INTO sys_role_menu VALUES (1, 2104);
INSERT INTO sys_role_menu VALUES (1, 2105);
