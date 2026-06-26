package com.ruoyi.common.filter;

import java.util.HashSet;
import java.util.Set;

/**
 * 排除JSON敏感属性（兼容无 fastjson 版本）
 *
 * @author ruoyi
 */
public class PropertyPreExcludeFilter
{
    private Set<String> excludes = new HashSet<>();

    public PropertyPreExcludeFilter()
    {
    }

    public PropertyPreExcludeFilter addExcludes(String... filters)
    {
        for (int i = 0; i < filters.length; i++)
        {
            this.excludes.add(filters[i]);
        }
        return this;
    }

    public Set<String> getExcludes()
    {
        return excludes;
    }

    public void setExcludes(Set<String> excludes)
    {
        this.excludes = excludes;
    }
}