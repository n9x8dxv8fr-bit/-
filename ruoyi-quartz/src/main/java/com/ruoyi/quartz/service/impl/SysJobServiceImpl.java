package com.ruoyi.quartz.service.impl;

import java.util.List;
import org.quartz.SchedulerException;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;

/**
 * 定时任务调度信息 服务层
 * 已移除 @Service，Spring 不会加载，修复启动
 *
 * @author ruoyi
 */
public class SysJobServiceImpl implements ISysJobService
{
    @Override
    public List<SysJob> selectJobList(SysJob job) {
        return null;
    }

    @Override
    public SysJob selectJobById(Long jobId) {
        return null;
    }

    @Override
    public int pauseJob(SysJob job) throws SchedulerException {
        return 0;
    }

    @Override
    public int resumeJob(SysJob job) throws SchedulerException {
        return 0;
    }

    @Override
    public int deleteJob(SysJob job) throws SchedulerException {
        return 0;
    }

    @Override
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
    }

    @Override
    public int changeStatus(SysJob job) throws SchedulerException {
        return 0;
    }

    @Override
    public boolean run(SysJob job) throws SchedulerException {
        return false;
    }

    @Override
    public int insertJob(SysJob job) throws SchedulerException, TaskException {
        return 0;
    }

    @Override
    public int updateJob(SysJob job) throws SchedulerException, TaskException {
        return 0;
    }

    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return true;
    }
}