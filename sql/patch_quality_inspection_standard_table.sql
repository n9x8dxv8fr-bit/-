-- Create quality_inspection_standard table only
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
