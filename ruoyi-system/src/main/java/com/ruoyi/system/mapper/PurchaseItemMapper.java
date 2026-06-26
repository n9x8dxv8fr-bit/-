package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.PurchaseItem;

/**
 * 采购明细Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface PurchaseItemMapper 
{
    /**
     * 查询采购明细
     * 
     * @param id 采购明细主键
     * @return 采购明细
     */
    public PurchaseItem selectPurchaseItemById(Long id);

    /**
     * 查询采购明细列表
     * 
     * @param purchaseItem 采购明细
     * @return 采购明细集合
     */
    public List<PurchaseItem> selectPurchaseItemList(PurchaseItem purchaseItem);

    /**
     * 新增采购明细
     * 
     * @param purchaseItem 采购明细
     * @return 结果
     */
    public int insertPurchaseItem(PurchaseItem purchaseItem);

    /**
     * 修改采购明细
     * 
     * @param purchaseItem 采购明细
     * @return 结果
     */
    public int updatePurchaseItem(PurchaseItem purchaseItem);

    /**
     * 删除采购明细
     * 
     * @param id 采购明细主键
     * @return 结果
     */
    public int deletePurchaseItemById(Long id);

    /**
     * 批量删除采购明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseItemByIds(Long[] ids);

    /**
     * 根据采购单ID删除采购明细
     *
     * @param orderId 采购单ID
     * @return 结果
     */
    public int deletePurchaseItemByOrderId(Long orderId);
}
