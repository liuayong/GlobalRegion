package com.littlefox.area.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceUtil {
    private final static Logger logger = LoggerFactory.getLogger(TraceUtil.class);


    private static StackTraceElement[] getStackTrace() {
        return Thread.currentThread().getStackTrace();
    }


    /**
     * @return
     */
    public static StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTrace = getStackTrace();
        int startIndex = 2;
        StackTraceElement traceElement = stackTrace[startIndex + 1];

        StackTraceElement stackTraceElement = stackTrace[startIndex];
        //logger.error(MessageFormat.format("className={0}, methodName={1}, fileName={2}, lineNumber={3}",
        //        stackTraceElement.getClassName(), stackTraceElement.getMethodName(),
        //        stackTraceElement.getFileName(), stackTraceElement.getLineNumber()));
        //logger.error(MessageFormat.format("className={0}, methodName={1}, fileName={2}, lineNumber={3}",
        //        getClassName(), getMethodName(),
        //        getFileName(), getLineNumber()));
        return traceElement;
    }


    public static String getFileName() {
        StackTraceElement[] stackTrace = getStackTrace();
        int startIndex = 3;
        return stackTrace[startIndex].getFileName();
    }

    public static int getLineNumber() {
        StackTraceElement[] stackTrace = getStackTrace();
        int startIndex = 3;
        return stackTrace[startIndex].getLineNumber();
    }

    public static String getClassName() {
        StackTraceElement[] stackTrace = getStackTrace();
        int startIndex = 3;
        return stackTrace[startIndex].getClassName();
    }

    public static String getMethodName() {
        StackTraceElement[] stackTrace = getStackTrace();
        int startIndex = 3;
        return stackTrace[startIndex].getMethodName();
    }


}
