package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PurchaseItem;

/**
 * 采购明细Service接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface IPurchaseItemService 
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
     * 批量删除采购明细
     * 
     * @param ids 需要删除的采购明细主键集合
     * @return 结果
     */
    public int deletePurchaseItemByIds(Long[] ids);

    /**
     * 删除采购明细信息
     * 
     * @param id 采购明细主键
     * @return 结果
     */
    public int deletePurchaseItemById(Long id);
}
