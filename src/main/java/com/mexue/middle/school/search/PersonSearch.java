package com.mexue.middle.school.search;

import lombok.Data;

@Data
public class PersonSearch extends BasePageSearch {
    private Long id;
    
    private String code;
    
    private String name;
    
    private Byte sex;
    
    private Short age;
    
    private String political;
    
    private String origin;
    
    private String professional;
}
