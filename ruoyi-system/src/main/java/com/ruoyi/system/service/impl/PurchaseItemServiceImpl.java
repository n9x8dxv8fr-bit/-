package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PurchaseItemMapper;
import com.ruoyi.system.domain.PurchaseItem;
import com.ruoyi.system.service.IPurchaseItemService;

/**
 * 采购明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class PurchaseItemServiceImpl implements IPurchaseItemService 
{
    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    /**
     * 查询采购明细
     * 
     * @param id 采购明细主键
     * @return 采购明细
     */
    @Override
    public PurchaseItem selectPurchaseItemById(Long id)
    {
        return purchaseItemMapper.selectPurchaseItemById(id);
    }

    /**
     * 查询采购明细列表
     * 
     * @param purchaseItem 采购明细
     * @return 采购明细
     */
    @Override
    public List<PurchaseItem> selectPurchaseItemList(PurchaseItem purchaseItem)
    {
        return purchaseItemMapper.selectPurchaseItemList(purchaseItem);
    }

    /**
     * 新增采购明细
     * 
     * @param purchaseItem 采购明细
     * @return 结果
     */
    @Override
    public int insertPurchaseItem(PurchaseItem purchaseItem)
    {
        return purchaseItemMapper.insertPurchaseItem(purchaseItem);
    }

    /**
     * 修改采购明细
     * 
     * @param purchaseItem 采购明细
     * @return 结果
     */
    @Override
    public int updatePurchaseItem(PurchaseItem purchaseItem)
    {
        return purchaseItemMapper.updatePurchaseItem(purchaseItem);
    }

    /**
     * 批量删除采购明细
     * 
     * @param ids 需要删除的采购明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseItemByIds(Long[] ids)
    {
        return purchaseItemMapper.deletePurchaseItemByIds(ids);
    }

    /**
     * 删除采购明细信息
     * 
     * @param id 采购明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseItemById(Long id)
    {
        return purchaseItemMapper.deletePurchaseItemById(id);
    }
}
