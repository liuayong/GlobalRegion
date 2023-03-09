package com.liao.learnjava.regex;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Template {
    public String template;
    private String regex = "\\$\\{\\s*(\\w+)\\s*}";
    
    
    public Template(String template) {
        this.template = template;
    }
    
    
    public String renderV1(Map<String, Object> data) {
        Pattern pattern = Pattern.compile(regex);
        String input = new String(template);
        Matcher matcher = pattern.matcher(input);
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() != null) {
                input = input.replaceAll("\\$\\{\\s*" + key + "\\s*}", entry.getValue().toString());
            }
        }
        return input;
    }
    
    public String renderV2(Map<String, Object> data) {
        Pattern pattern = Pattern.compile(regex);
        String input = template;
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            log.info("input={}, group={}, groupCount={}", matcher.group(), matcher.group(1), matcher.groupCount());
            String key = matcher.group(1);
            if (data.containsKey(key)) {
                input = input.replaceAll("\\$\\{" + matcher.group().substring(2), data.get(key).toString());
            }
        }
        
        return input;
    }
    
    public String render(Map<String, Object> data) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String key = matcher.group(1);
            Object result = data.get(key);
            if (result != null) {
                matcher.appendReplacement(sb, result.toString());
                log.info("key={}, result={}, sb={}", key, result, sb);
            }
        }
        matcher.appendTail(sb);
        log.info("sb={}", sb);
        return sb.toString();
    }
    
}
