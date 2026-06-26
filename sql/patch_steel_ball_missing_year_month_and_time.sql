-- 根据当前表结构补全：year_month、create_time、update_time
-- remark 若已存在会 Duplicate，跳过该行
-- 列名 year 为保留字相关，必须用反引号

ALTER TABLE steel_ball_inout_flow ADD COLUMN `year_month` VARCHAR(16) NULL COMMENT 'YYYY-MM';

-- 若表里没有 create_time / update_time，再执行下面两行；若已有会 Duplicate，跳过
ALTER TABLE steel_ball_inout_flow ADD COLUMN `create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间';
ALTER TABLE steel_ball_inout_flow ADD COLUMN `update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间';

-- 仅当 SHOW COLUMNS 中确实没有 remark 时再执行；已有则跳过
-- ALTER TABLE steel_ball_inout_flow ADD COLUMN `remark` VARCHAR(512) NULL;
