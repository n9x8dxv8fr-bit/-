package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TProductMapper;
import com.ruoyi.system.domain.TProduct;
import com.ruoyi.system.service.ITProductService;

/**
 * 钢球产品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-28
 */
@Service
public class TProductServiceImpl implements ITProductService 
{
    @Autowired
    private TProductMapper tProductMapper;

    /**
     * 查询钢球产品信息
     * 
     * @param id 钢球产品信息主键
     * @return 钢球产品信息
     */
    @Override
    public TProduct selectTProductById(String id)
    {
        return tProductMapper.selectTProductById(id);
    }

    /**
     * 查询钢球产品信息列表
     * 
     * @param tProduct 钢球产品信息
     * @return 钢球产品信息
     */
    @Override
    public List<TProduct> selectTProductList(TProduct tProduct)
    {
        return tProductMapper.selectTProductList(tProduct);
    }

    /**
     * 新增钢球产品信息
     * 
     * @param tProduct 钢球产品信息
     * @return 结果
     */
    @Override
    public int insertTProduct(TProduct tProduct)
    {
        tProduct.setCreateTime(DateUtils.getNowDate());
        return tProductMapper.insertTProduct(tProduct);
    }

    /**
     * 修改钢球产品信息
     * 
     * @param tProduct 钢球产品信息
     * @return 结果
     */
    @Override
    public int updateTProduct(TProduct tProduct)
    {
        tProduct.setUpdateTime(DateUtils.getNowDate());
        return tProductMapper.updateTProduct(tProduct);
    }

    /**
     * 批量删除钢球产品信息
     * 
     * @param ids 需要删除的钢球产品信息主键
     * @return 结果
     */
    @Override
    public int deleteTProductByIds(String[] ids)
    {
        return tProductMapper.deleteTProductByIds(ids);
    }

    /**
     * 删除钢球产品信息信息
     * 
     * @param id 钢球产品信息主键
     * @return 结果
     */
    @Override
    public int deleteTProductById(String id)
    {
        return tProductMapper.deleteTProductById(id);
    }
}
