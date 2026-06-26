package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

// 关键：排除 代码生成器 + 定时任务 包，不让Spring加载！
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(
        basePackages = "com.ruoyi",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.ruoyi.generator.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.ruoyi.quartz.*")
        }
)
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("项目启动成功！");
    }
}