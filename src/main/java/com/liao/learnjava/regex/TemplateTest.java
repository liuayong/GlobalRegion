package com.liao.learnjava.regex;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplateTest {
    
    @Test
    public void testTemplate() {
        Template t = new Template("Hello, ${name}! You are learning ${lang}!");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Bob");
        data.put("lang", "Java");
        //assertEquals("Hello, Bob! You are learning Java!", t.render(data));
    }
    
    @Test
    public void testTemplate2() {
        String input = "Hello, ${name}! You are learning ${lang }!  测试 ${name}  hello ${name } world ${ name   } " +
                "${ok} !!! ";
        String input2 = "Hello,     ${name}! You are   learning ${lang}!!!";
        Template t = new Template(input);
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Bob");
        data.put("lang", "Java");
        data.put("hi", "阿勇");
        data.put("good", new Date());
        
        System.out.println(t.render(data));
        //System.out.println("template: " + t.template);
        //System.out.println(t.renderV1(data));
        //System.out.println("template: " + t.template);
        //System.out.println(t.renderV2(data));
        //System.out.println("template: " + t.template);
    
    }
    
}
