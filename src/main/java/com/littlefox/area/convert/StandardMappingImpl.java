package com.littlefox.area.convert;

public class StandardMappingImpl<S extends Object, T extends Object> implements Mapping<S, T> {
    
    /**
     * 映射源
     */
    private S source;
    
    /**
     * 映射目标
     */
    private T target;
    
    public StandardMappingImpl(S source, T target) {
        this.source = source;
        this.target = target;
    }
    
    @Override
    public S getSource() {
        return source;
    }
    
    @Override
    public void setSource(S source) {
        this.source = source;
    }
    
    @Override
    public T getTarget() {
        return target;
    }
    
    @Override
    public void setTarget(T target) {
        this.target = target;
    }
    
    @Override
    public Mapping<T, S> reverse() {
        return new StandardMappingImpl<T, S>(this.target, this.source);
    }
    
}
