package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ParamMapUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.PurchaseOrder;
import com.ruoyi.system.domain.SteelBallInoutFlow;
import com.ruoyi.system.mapper.SteelBallInoutFlowMapper;
import com.ruoyi.system.service.ISteelBallInoutFlowRecordService;

@Service
public class SteelBallInoutFlowRecordServiceImpl implements ISteelBallInoutFlowRecordService
{
    private static final long IN_TYPE = 1L;
    private static final long OUT_TYPE = 2L;

    @Autowired
    private SteelBallInoutFlowMapper steelBallInoutFlowMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordOutbound(String productType, String productSpec, Long quantity, BigDecimal unitPrice,
            BigDecimal totalAmount, String customerName, String orderNo, String remark)
    {
        String pt = StringUtils.trimToEmpty(productType);
        String ps = StringUtils.trimToEmpty(productSpec);
        String productName = aggregateProductName(pt, ps);
        Long qty = quantity == null ? 0L : quantity;
        BigDecimal up = unitPrice == null ? BigDecimal.ZERO : unitPrice;
        BigDecimal ta = totalAmount == null ? BigDecimal.ZERO : totalAmount;
        String cust = StringUtils.trimToEmpty(customerName);
        String ono = StringUtils.trimToEmpty(orderNo);

        Date now = DateUtils.getNowDate();
        String yearMonth = new SimpleDateFormat("yyyy-MM").format(now);
        String operator = resolveOperator();

        Map<String, Object> inMap = ParamMapUtil.buildParamMap("productName", productName, "inoutType", IN_TYPE);
        Long productTotalIn = nzLong(steelBallInoutFlowMapper.selectSumQuantityByProduct(inMap));
        BigDecimal productTotalInAmount = nzDecimal(steelBallInoutFlowMapper.selectSumAmountByProduct(inMap));

        Map<String, Object> outMap = ParamMapUtil.buildParamMap("productName", productName, "inoutType", OUT_TYPE);
        Long productTotalOut = nzLong(steelBallInoutFlowMapper.selectSumQuantityByProduct(outMap)) + qty;
        BigDecimal productTotalOutAmount = nzDecimal(steelBallInoutFlowMapper.selectSumAmountByProduct(outMap)).add(ta);

        Long customerTotalOut = 0L;
        BigDecimal customerTotalOutAmount = BigDecimal.ZERO;
        if (StringUtils.isNotEmpty(cust))
        {
            Map<String, Object> custMap = ParamMapUtil.buildParamMap("customerName", cust, "inoutType", OUT_TYPE);
            customerTotalOut = nzLong(steelBallInoutFlowMapper.selectSumQuantityByCustomer(custMap)) + qty;
            customerTotalOutAmount = nzDecimal(steelBallInoutFlowMapper.selectSumAmountByCustomer(custMap)).add(ta);
        }

        SteelBallInoutFlow row = new SteelBallInoutFlow();
        row.setFlowNo("FLOW-OUT-" + System.currentTimeMillis());
        row.setProductName(productName);
        row.setProductType(StringUtils.isNotEmpty(pt) ? pt : null);
        row.setProductSpec(StringUtils.isNotEmpty(ps) ? ps : null);
        row.setInoutType(OUT_TYPE);
        row.setQuantity(qty);
        row.setUnitPrice(up);
        row.setTotalAmount(ta);
        row.setProductTotalIn(productTotalIn);
        row.setProductTotalInAmount(productTotalInAmount);
        row.setProductTotalOut(productTotalOut);
        row.setProductTotalOutAmount(productTotalOutAmount);
        row.setCustomerTotalOut(customerTotalOut);
        row.setCustomerTotalOutAmount(customerTotalOutAmount);
        row.setYearMonth(yearMonth);
        row.setCustomerName(StringUtils.isNotEmpty(cust) ? cust : null);
        row.setOrderNo(StringUtils.isNotEmpty(ono) ? ono : null);
        row.setOperateTime(now);
        row.setOperator(operator);
        row.setRemark(remark);
        row.setCreateTime(now);
        row.setUpdateTime(now);
        steelBallInoutFlowMapper.insertSteelBallInoutFlow(row);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordInboundFromPurchase(PurchaseOrder order)
    {
        if (order == null || order.getId() == null)
        {
            return;
        }
        String pt = StringUtils.trimToEmpty(order.getProductType());
        String ps = StringUtils.trimToEmpty(order.getProductSpec());
        if (StringUtils.isEmpty(pt) && StringUtils.isEmpty(ps))
        {
            pt = "采购汇总";
            ps = StringUtils.isNotEmpty(order.getOrderNo()) ? order.getOrderNo() : String.valueOf(order.getId());
        }
        String productName = aggregateProductName(pt, ps);
        Long qty = order.getTotalQuantity() == null ? 0L : order.getTotalQuantity();
        BigDecimal ta = order.getTotalAmount() == null ? BigDecimal.ZERO : order.getTotalAmount();
        BigDecimal up = BigDecimal.ZERO;
        if (qty > 0)
        {
            up = ta.divide(BigDecimal.valueOf(qty), 2, java.math.RoundingMode.HALF_UP);
        }

        Date now = order.getPurchaseTime() != null ? order.getPurchaseTime() : DateUtils.getNowDate();
        String yearMonth = new SimpleDateFormat("yyyy-MM").format(now);
        String operator = StringUtils.isNotEmpty(order.getOperator()) ? order.getOperator() : resolveOperator();
        String ono = StringUtils.trimToEmpty(order.getOrderNo());
        String remark = "采购入库：" + (StringUtils.isNotEmpty(ono) ? ono : order.getId());

        Map<String, Object> inMap = ParamMapUtil.buildParamMap("productName", productName, "inoutType", IN_TYPE);
        Long productTotalIn = nzLong(steelBallInoutFlowMapper.selectSumQuantityByProduct(inMap)) + qty;
        BigDecimal productTotalInAmount = nzDecimal(steelBallInoutFlowMapper.selectSumAmountByProduct(inMap)).add(ta);

        Map<String, Object> outMap = ParamMapUtil.buildParamMap("productName", productName, "inoutType", OUT_TYPE);
        Long productTotalOut = nzLong(steelBallInoutFlowMapper.selectSumQuantityByProduct(outMap));
        BigDecimal productTotalOutAmount = nzDecimal(steelBallInoutFlowMapper.selectSumAmountByProduct(outMap));

        SteelBallInoutFlow row = new SteelBallInoutFlow();
        row.setFlowNo("FLOW-IN-" + System.currentTimeMillis());
        row.setProductName(productName);
        row.setProductType(StringUtils.isNotEmpty(pt) ? pt : null);
        row.setProductSpec(StringUtils.isNotEmpty(ps) ? ps : null);
        row.setInoutType(IN_TYPE);
        row.setQuantity(qty);
        row.setUnitPrice(up);
        row.setTotalAmount(ta);
        row.setProductTotalIn(productTotalIn);
        row.setProductTotalInAmount(productTotalInAmount);
        row.setProductTotalOut(productTotalOut);
        row.setProductTotalOutAmount(productTotalOutAmount);
        row.setCustomerTotalOut(0L);
        row.setCustomerTotalOutAmount(BigDecimal.ZERO);
        row.setYearMonth(yearMonth);
        row.setCustomerName(null);
        row.setOrderNo(StringUtils.isNotEmpty(ono) ? ono : null);
        row.setOperateTime(now);
        row.setOperator(operator);
        row.setRemark(remark);
        row.setCreateTime(DateUtils.getNowDate());
        row.setUpdateTime(DateUtils.getNowDate());
        steelBallInoutFlowMapper.insertSteelBallInoutFlow(row);
    }

    private static String aggregateProductName(String productType, String productSpec)
    {
        String key = productType + productSpec;
        if (StringUtils.isEmpty(key))
        {
            return "未指定产品";
        }
        return key;
    }

    private static Long nzLong(Long v)
    {
        return v == null ? 0L : v;
    }

    private static BigDecimal nzDecimal(BigDecimal v)
    {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static String resolveOperator()
    {
        try
        {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isBlank(name) || Objects.equals("anonymousUser", name))
            {
                return "system";
            }
            return name;
        }
        catch (Exception e)
        {
            return "system";
        }
    }
}
