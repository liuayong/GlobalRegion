package com.littlefox.area.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileConfig {
    
    @Autowired
    private ApplicationContext context;
    
    public String getActiveProfile() {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        if(activeProfiles.length == 0) {
            System.out.println("没有配置profiles: activeProfiles " );
            return "dev" ;
        }
        
        return activeProfiles[0];
    }
}

