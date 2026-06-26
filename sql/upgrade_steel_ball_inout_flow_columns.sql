-- 钢球进出库流水表补充字段。含 YEAR/ORDER/OUT/IN 等保留字风险的列名一律用反引号。
-- 若某列已存在会 Duplicate column，跳过该条即可。

ALTER TABLE steel_ball_inout_flow ADD COLUMN `product_total_in` BIGINT NULL COMMENT '该产品累计入库数量' AFTER `total_amount`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `product_total_in_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该产品累计入库金额' AFTER `product_total_in`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `product_total_out` BIGINT NULL COMMENT '该产品累计出库数量' AFTER `product_total_in_amount`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `product_total_out_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该产品累计出库金额' AFTER `product_total_out`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `customer_total_out` BIGINT NULL COMMENT '该客户累计出库数量' AFTER `product_total_out_amount`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `customer_total_out_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该客户累计出库金额' AFTER `customer_total_out`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `year_month` VARCHAR(16) NULL COMMENT '年月YYYY-MM' AFTER `customer_total_out_amount`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `customer_name` VARCHAR(128) NULL COMMENT '关联客户' AFTER `year_month`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `order_no` VARCHAR(64) NULL COMMENT '关联单号' AFTER `customer_name`;
ALTER TABLE steel_ball_inout_flow ADD COLUMN `operate_time` DATETIME NULL COMMENT '操作时间' AFTER `order_no`;
-- 若已有 remark 列则注释本行。不用 AFTER，避免 operate_time 未建好时整条语法失败
ALTER TABLE steel_ball_inout_flow ADD COLUMN `remark` VARCHAR(512) NULL COMMENT '备注';
