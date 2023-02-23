package com.hspedu.string;

import com.google.common.base.CharMatcher;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class StringDemo {
    @Test
    public void countMatches1() {
        String someString = "eleabapbabahant";
        char someChar = 'e';
        int count = 0;
        
        for (int i = 0; i < someString.length(); i++) {
            if (someString.charAt(i) == someChar) {
                count++;
            }
        }
        assertEquals(2, count);
        
        System.out.println(countOccurences(someString, someChar, 0));
        
    }
    
    /**
     * 递归
     *
     * @param someString
     * @param searchedChar
     * @param index
     * @return
     */
    private static int countOccurences(
            String someString, char searchedChar, int index) {
        if (index >= someString.length()) { // 递归的出口
            return 0;
        }
        
        int count = someString.charAt(index) == searchedChar ? 1 : 0;
        return count + countOccurences(
                someString, searchedChar, index + 1);
    }
    
    @Test
    public void countMatches2() {
        String someString = "eleabapbabahabnt";
        String subStr = "ab";
        int count = 0;
        
        for (int i = 0, len = someString.length(); i < len; i++) {
            int endIndex = Math.min(i + subStr.length(), len);
            if (subStr.equals(someString.substring(i, endIndex))) {
                count++;
            }
        }
        assertEquals(3, count);
        
        
    }
    
    @Test
    public void countMatches3() {
        Pattern pattern = Pattern.compile("[^e]*e");
        Matcher matcher = pattern.matcher("elephaente");
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        
        assertEquals(4, count);
    }
    
    @Test
    public void countMatches4() {
        String someString = "elephant";
        long count = someString.chars().filter(ch -> ch == 'e').count();
        assertEquals(2, count);
        
        long count2 = someString.codePoints().filter(ch -> ch == 'e').count();
        assertEquals(2, count2);
    }
    
    
    @Test
    public void countMatches5() {
        String str = "eleabapbabahabnt";
        String subStr = "ab";
        int i1 = StringUtils.countOccurrencesOf(str, subStr);
        System.out.println("i1 = " + i1);
        
        int i2 = org.apache.commons.lang3.StringUtils.countMatches(str, subStr);
        System.out.println("i2 = " + i2);
        System.out.println(str.contains(subStr));
        
    }
}






