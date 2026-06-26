-- 列名 year_month 以保留字 YEAR 开头，MySQL 中必须用反引号。
-- order_no 含 ORDER，亦建议反引号。逐条执行；Duplicate 则跳过该条。

ALTER TABLE steel_ball_inout_flow ADD COLUMN `year_month` VARCHAR(16) NULL COMMENT '年月YYYY-MM';
ALTER TABLE steel_ball_inout_flow ADD COLUMN `customer_name` VARCHAR(128) NULL COMMENT '关联客户';
ALTER TABLE steel_ball_inout_flow ADD COLUMN `order_no` VARCHAR(64) NULL COMMENT '关联单号';
ALTER TABLE steel_ball_inout_flow ADD COLUMN `operate_time` DATETIME NULL COMMENT '操作时间';
-- 若已有 remark 列则注释本行（若依表常带 remark，会 Duplicate column）
ALTER TABLE steel_ball_inout_flow ADD COLUMN `remark` VARCHAR(512) NULL;
