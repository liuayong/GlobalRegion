package com.hspedu.json.jackson;

public class DateTypeUtil {

    public enum DateType{
        ISO_LOCAL_DATE_TIME,
        EPOCH_MILLI
    }

    public static ThreadLocal<DateType> dateTypeThreadLocal = new ThreadLocal<DateType>(){
        @Override
        protected DateType initialValue() {
            return DateType.EPOCH_MILLI;
        }
    };
}
