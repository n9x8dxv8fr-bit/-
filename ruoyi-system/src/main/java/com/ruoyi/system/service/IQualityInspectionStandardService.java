package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.QualityInspectionStandard;

/**
 * �ʼ��׼Service�ӿ�
 *
 * @author ruoyi
 */
public interface IQualityInspectionStandardService
{
    QualityInspectionStandard selectQualityInspectionStandardById(Long id);

    List<QualityInspectionStandard> selectQualityInspectionStandardList(QualityInspectionStandard qualityInspectionStandard);

    int insertQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard);

    int updateQualityInspectionStandard(QualityInspectionStandard qualityInspectionStandard);

    int deleteQualityInspectionStandardByIds(Long[] ids);

    int deleteQualityInspectionStandardById(Long id);
}
