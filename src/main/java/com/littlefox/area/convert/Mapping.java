package com.littlefox.area.convert;

/**
 * 映射
 *
 * @param <Source>
 * @param <Target>
 */
public interface Mapping<Source, Target> {
    
    /**
     * 数据源
     *
     * @return 源
     */
    Source getSource();
    
    /**
     * 设置源
     *
     * @param source
     */
    void setSource(Source source);
    
    /**
     * 获取目标
     *
     * @return 设置目标
     */
    Target getTarget();
    
    /**
     * 设置目标
     *
     * @param target 目标
     */
    void setTarget(Target target);
    
    /**
     * 反转映射
     *
     * @return 映射反转
     */
    Mapping<Target, Source> reverse();
}
