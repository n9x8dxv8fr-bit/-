-- 采购单：产品与订购/销售/流水聚合键对齐（可空；空则按「采购汇总+单号」生成明细与入库流水）
-- 若列已存在，请删除或注释对应行后再执行。

ALTER TABLE purchase_order
    ADD COLUMN product_type VARCHAR(128) NULL COMMENT '产品类型' AFTER supplier_id,
    ADD COLUMN product_spec VARCHAR(128) NULL COMMENT '产品规格' AFTER product_type;

-- 钢球进出库流水：累计统计与关联字段（列名含 YEAR/ORDER/OUT 等须反引号）
ALTER TABLE steel_ball_inout_flow
    ADD COLUMN `product_total_in` BIGINT NULL COMMENT '该产品累计入库数量' AFTER `total_amount`,
    ADD COLUMN `product_total_in_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该产品累计入库金额' AFTER `product_total_in`,
    ADD COLUMN `product_total_out` BIGINT NULL COMMENT '该产品累计出库数量' AFTER `product_total_in_amount`,
    ADD COLUMN `product_total_out_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该产品累计出库金额' AFTER `product_total_out`,
    ADD COLUMN `customer_total_out` BIGINT NULL COMMENT '该客户累计出库数量' AFTER `product_total_out_amount`,
    ADD COLUMN `customer_total_out_amount` DECIMAL(18, 2) NULL DEFAULT 0 COMMENT '该客户累计出库金额' AFTER `customer_total_out`,
    ADD COLUMN `year_month` VARCHAR(16) NULL COMMENT '年月' AFTER `customer_total_out_amount`,
    ADD COLUMN `customer_name` VARCHAR(128) NULL COMMENT '关联客户' AFTER `year_month`,
    ADD COLUMN `order_no` VARCHAR(64) NULL COMMENT '关联单号' AFTER `customer_name`,
    ADD COLUMN `operate_time` DATETIME NULL COMMENT '操作时间' AFTER `order_no`,
    ADD COLUMN `remark` VARCHAR(512) NULL COMMENT '备注';
