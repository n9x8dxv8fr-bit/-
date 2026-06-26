-- 将采购/销售订单和明细中的ID业务字段改为字符型，支持填写文字

ALTER TABLE purchase_order MODIFY COLUMN supplier_id varchar(64) DEFAULT NULL COMMENT '供应商ID（可填写文字）';
ALTER TABLE sale_order MODIFY COLUMN customer_id varchar(64) DEFAULT NULL COMMENT '客户ID（可填写文字）';
ALTER TABLE purchase_item MODIFY COLUMN product_id varchar(64) DEFAULT NULL COMMENT '产品ID（可填写文字）';
ALTER TABLE sale_item MODIFY COLUMN product_id varchar(64) DEFAULT NULL COMMENT '产品ID（可填写文字）';
