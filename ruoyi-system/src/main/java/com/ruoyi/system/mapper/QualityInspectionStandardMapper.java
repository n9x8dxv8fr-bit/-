package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.QualityInspectionStandard;

/**
 * �ʼ��׼Mapper�ӿ�
 *
 * @author ruoyi
 */
public interface QualityInspectionStandardMapper
{
    QualityInspectionStandard selectQualityInspectionStandardById(Long id);

    List<QualityInspectionStandard> selectQualityInspectionStandardList(QualityInspectionStandard qualityInspectionStandard);

    int insertQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard);

    int updateQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard);

    int deleteQualityInspectionStandardById(Long id);

    int deleteQualityInspectionStandardByIds(Long[] ids);
}
