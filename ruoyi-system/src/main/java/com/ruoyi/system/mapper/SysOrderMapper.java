package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOrderMapper {
    int insertSysOrder(SysOrder order);

    SysOrder selectSysOrderById(@Param("id") Long id);

    List<SysOrder> selectSysOrderList(SysOrder order);

    int updateSysOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("remark") String remark);

    int countByStatus(@Param("status") String status);
}