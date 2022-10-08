package com.mexue.middle.school.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hspedu.json.jackson.PersonSerialize;
import com.mexue.middle.school.util.BeanUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PersonCopy extends Person {
    
    public static PersonCopy convert(Person person) {
        return BeanUtil.convert(person, PersonCopy.class);
    }
    
    
    
    public String toString2() {
        return "PersonCopy{" +
                "id=" + getId() +
                ", code='" + getCode() + '\'' +
                ", name='" + getName() + '\'' +
                ", sex=" + getSex() +
                ", age=" + getAge() +
                ", political='" + getPolitical() + '\'' +
                ", origin='" + getOrigin() + '\'' +
                ", professional='" + getProfessional() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", createDate=" + getCreateDate() +
                '}';
    }
}