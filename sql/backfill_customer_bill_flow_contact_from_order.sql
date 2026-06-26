-- 历史数据回填：根据订单号把客户账单流水电话/地址补齐
-- 前提：customer_bill_flow 已存在 customer_phone/customer_address 字段

UPDATE customer_bill_flow cbf
INNER JOIN sys_order so ON so.order_no = cbf.related_order_no
SET
    cbf.customer_phone = so.customer_phone,
    cbf.customer_address = so.customer_address
WHERE
    (cbf.customer_phone IS NULL OR cbf.customer_phone = '')
    OR (cbf.customer_address IS NULL OR cbf.customer_address = '');
