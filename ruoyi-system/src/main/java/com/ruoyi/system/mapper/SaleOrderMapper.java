package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SaleOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 销售订单Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface SaleOrderMapper 
{
    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    public SaleOrder selectSaleOrderById(Long id);

    /**
     * 查询销售订单列表
     * 
     * @param saleOrder 销售订单
     * @return 销售订单集合
     */
    public List<SaleOrder> selectSaleOrderList(SaleOrder saleOrder);

    /**
     * 新增销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    public int insertSaleOrder(SaleOrder saleOrder);

    /**
     * 修改销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    public int updateSaleOrder(SaleOrder saleOrder);

    /**
     * 删除销售订单
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    public int deleteSaleOrderById(Long id);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSaleOrderByIds(Long[] ids);

    /**
     * 统计昨日销量（总数量）
     */
    Long selectYesterdayTotalQuantity(@Param("start") String start, @Param("end") String end);
}
