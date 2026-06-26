package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QualityInspectionStandardMapper;
import com.ruoyi.system.domain.QualityInspectionStandard;
import com.ruoyi.system.service.IQualityInspectionStandardService;

/**
 * �ʼ��׼Serviceҵ��㴦��
 *
 * @author ruoyi
 */
@Service
public class QualityInspectionStandardServiceImpl implements IQualityInspectionStandardService
{
    @Autowired
    private QualityInspectionStandardMapper qualityInspectionStandardMapper;

    @Override
    public QualityInspectionStandard selectQualityInspectionStandardById(Long id)
    {
        return qualityInspectionStandardMapper.selectQualityInspectionStandardById(id);
    }

    @Override
    public List<QualityInspectionStandard> selectQualityInspectionStandardList(QualityInspectionStandard qualityInspectionStandard)
    {
        return qualityInspectionStandardMapper.selectQualityInspectionStandardList(qualityInspectionStandard);
    }

    @Override
    public int insertQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard)
    {
        qualityInspectionStandard.setCreateTime(DateUtils.getNowDate());
        return qualityInspectionStandardMapper.insertQualityInspectionStandard(qualityInspectionStandard);
    }

    @Override
    public int updateQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard)
    {
        qualityInspectionStandard.setUpdateTime(DateUtils.getNowDate());
        return qualityInspectionStandardMapper.updateQualityInspectionStandard(qualityInspectionStandard);
    }

    @Override
    public int deleteQualityInspectionStandardByIds(Long[] ids)
    {
        return qualityInspectionStandardMapper.deleteQualityInspectionStandardByIds(ids);
    }

    @Override
    public int deleteQualityInspectionStandardById(Long id)
    {
        return qualityInspectionStandardMapper.deleteQualityInspectionStandardById(id);
    }
}
