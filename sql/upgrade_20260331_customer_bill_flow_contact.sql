-- 客户账单流水补充客户联系方式字段
-- 执行库：当前业务库（含 customer_bill_flow 表）

ALTER TABLE customer_bill_flow
    ADD COLUMN customer_phone varchar(20) DEFAULT NULL COMMENT '客户电话' AFTER customer_name,
    ADD COLUMN customer_address varchar(255) DEFAULT NULL COMMENT '客户地址' AFTER customer_phone;
