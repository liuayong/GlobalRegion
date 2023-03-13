package com.mexue.middle.school.mapper;

import com.mexue.middle.school.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefIdMapper {

    String test1(@Param(value = "luck") String luck);

    String test2(@Param(value = "luck") String luck);

    String test3(@Param(value = "luck") String luck);

    String test4(@Param(value = "luck") String luck);

}