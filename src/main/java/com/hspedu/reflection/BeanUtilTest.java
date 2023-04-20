package com.hspedu.reflection;

import com.byd.tool.PrintUtil;
import com.hspedu.util.ReflectionUtils;
import com.mexue.middle.school.util.BeanUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanUtilTest {
    @Test
    public void test0() {
        Person per = null;

        PrintUtil.println(BeanUtil.getNullPropertyNames2(per));
        String[] nullPropertyNames = BeanUtil.getNullPropertyNames(per);
        PrintUtil.println(nullPropertyNames);


    }


    @Test
    public void copyNull1() {
        Person per = new Person();
        PrintUtil.println(per);

        String[] nullPropertyNames = BeanUtil.getNullPropertyNames(per);
        PrintUtil.println(nullPropertyNames);

        List<String> allFieldNames = com.hspedu.util.ReflectionUtils.getAllFieldNames(Person.class);
        PrintUtil.println(allFieldNames, 10);
        Set<String> emptyNames = new HashSet<String>();
        for (String fieldName : allFieldNames) {
            Object fieldValue = ReflectionUtils.getFieldValue(per, fieldName);
            if (fieldValue == null) {
                emptyNames.add(fieldName);
            }
        }
        String[] result = new String[emptyNames.size()];
        PrintUtil.println(emptyNames.toArray(result));
        PrintUtil.println(emptyNames);

    }

    @Test
    public void copyNull2() {
        Person per = new Person();
        per.setSal(999.0);
        per.setPerName("刘阿勇");
        per.setJob("softWare Engineer");
        per.setInnerAofAge(111);
        per.setAnInt(222);


        PersonCopy personCopy = new PersonCopy();
        personCopy.setSal(888.0);
        personCopy.setAnInt(10);

        PrintUtil.println(personCopy);

        // 只拷贝target的为null的属性， target不为null的属性不会受到覆盖影响
        BeanUtil.copyNullProperties(per, personCopy);
        PrintUtil.println(personCopy);


        // 只拷贝source的不为null的属性， 可以覆盖target不为null的属性
        BeanUtil.copyPropertiesIgnoreNull(per, personCopy);
        PrintUtil.println(personCopy);



    }


}
