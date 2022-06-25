package com.mexue.middle.school.search;

public enum Direction {
    ASC("ASC", "升序"),
    DESC("DESC", "降序");
    private String code;
    private String desc;
    private Direction(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return this.code;
    }
    public String getDesc() {
        return this.desc;
    }
    public static Direction fromCode(String code) {
        Direction[] values = values();
        for(int i = 0; i < values.length; ++i) {
            if (code.equals(values[i].code)) {
                return values[i];
            }
        }
        return null;
    }
    public static Direction fromCodeOrDesc(String code) {
        Direction[] values = values();
        for(int i = 0; i < values.length; ++i) {
            if (code.equals(values[i].code) || code.equals(values[i].desc)) {
                return values[i];
            }
        }
        return null;
    }
}