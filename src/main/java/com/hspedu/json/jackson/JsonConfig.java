package com.hspedu.json.jackson;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.*;
import java.util.Calendar;
import java.util.Date;


@Configuration
public class JsonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = converter.getObjectMapper();

        //通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        //Include.Include.ALWAYS 默认
        //Include.NON_DEFAULT 属性为默认值不序列化
        //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
        //Include.NON_NULL 属性为NULL 不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.setDateFormat(new CustomSimpleDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));

//        // 为mapper注册一个带有SerializerModifier的Factory，此modifier主要做的事情为：当序列化类型为array，list、set时，当值为空时，序列化成[]
//        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new InwilBeanSerializerModifier()));

        return converter;
    }

    static class CustomSimpleDateFormat extends DateFormat {

        private DateFormat dateFormat;

        public CustomSimpleDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
            if (DateTypeUtil.dateTypeThreadLocal.get().equals(DateTypeUtil.DateType.EPOCH_MILLI)) {
                return new StringBuffer(String.valueOf(date.getTime()));
            } else {
                return dateFormat.format(date, toAppendTo, fieldPosition);
            }
        }

        @Override
        public Date parse(String source, ParsePosition pos) {
            return dateFormat.parse(source, pos);
        }

        @Override
        public Object clone() {
            DateFormat otherDateFormat = (DateFormat) dateFormat.clone();
            CustomSimpleDateFormat customSimpleDateFormat = new CustomSimpleDateFormat(otherDateFormat);
            customSimpleDateFormat.calendar = (Calendar) otherDateFormat.getCalendar().clone();
            customSimpleDateFormat.numberFormat = (NumberFormat) otherDateFormat.getNumberFormat().clone();
            return customSimpleDateFormat;
        }
    }
}
