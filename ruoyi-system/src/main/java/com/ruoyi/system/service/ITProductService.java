package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TProduct;

/**
 * 钢球产品信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-28
 */
public interface ITProductService 
{
    /**
     * 查询钢球产品信息
     * 
     * @param id 钢球产品信息主键
     * @return 钢球产品信息
     */
    public TProduct selectTProductById(String id);

    /**
     * 查询钢球产品信息列表
     * 
     * @param tProduct 钢球产品信息
     * @return 钢球产品信息集合
     */
    public List<TProduct> selectTProductList(TProduct tProduct);

    /**
     * 新增钢球产品信息
     * 
     * @param tProduct 钢球产品信息
     * @return 结果
     */
    public int insertTProduct(TProduct tProduct);

    /**
     * 修改钢球产品信息
     * 
     * @param tProduct 钢球产品信息
     * @return 结果
     */
    public int updateTProduct(TProduct tProduct);

    /**
     * 批量删除钢球产品信息
     * 
     * @param ids 需要删除的钢球产品信息主键集合
     * @return 结果
     */
    public int deleteTProductByIds(String[] ids);

    /**
     * 删除钢球产品信息信息
     * 
     * @param id 钢球产品信息主键
     * @return 结果
     */
    public int deleteTProductById(String id);
}
