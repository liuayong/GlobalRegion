package com.hspedu.service;

import com.hspedu.annotation.BankAPI;
import com.hspedu.annotation.BankAPIField;
import com.hspedu.pojo.AbstractAPI;
import com.hspedu.pojo.CreateUserAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://mp.weixin.qq.com/s/r2BLbeqXfEWHZLmCwjiYwA
 */
@Slf4j
public class BankService {


    /**
     * @param name
     * @param identity
     * @param mobile
     * @param age
     * @return
     * @throws IOException
     */

    //创建用户方法
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        StringBuffer StringBuffer = new StringBuffer();
        //字符串靠左，多余的地方填充_
        StringBuffer.append(String.format("%-10s", name).replace(' ', '_'));
        //字符串靠左，多余的地方填充_
        StringBuffer.append(String.format("%-18s", identity).replace(' ', '_'));
        //数字靠右，多余的地方用0填充
        StringBuffer.append(String.format("%05d", age));
        //字符串靠左，多余的地方用_填充
        StringBuffer.append(String.format("%-11s", mobile).replace(' ', '_'));

        //最后加上MD5作为签名
        StringBuffer.append(DigestUtils.md2Hex(StringBuffer.toString()));

        return StringBuffer.toString();
        // return Request.Post("http://localhost:45678/reflection/bank/createUser")
        //         .bodyString(StringBuffer.toString(), ContentType.APPLICATION_JSON)
        //         .execute().returnContent().asString();
    }

    //支付方法
    public static String pay(long userId, BigDecimal amount) throws IOException {
        StringBuffer StringBuffer = new StringBuffer();
        //数字靠右，多余的地方用0填充
        StringBuffer.append(String.format("%020d", userId));
        //金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
        StringBuffer.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));

        //最后加上MD5作为签名
        StringBuffer.append(DigestUtils.md2Hex(StringBuffer.toString()));

        return StringBuffer.toString();
        // return Request.Post("http://localhost:45678/reflection/bank/pay")
        //         .bodyString(StringBuffer.toString(), ContentType.APPLICATION_JSON)
        //         .execute().returnContent().asString();
    }

    public static void main(String[] args) throws IOException {
        String name = "liuayong";
        String identity = "4307031111";
        String mobile = "13718526927";
        int age = 18;

        String user = createUser(name, identity, mobile, age);

        // String replace = String.format("%-12s", mobile).replace(' ', '_');
        // System.out.println(replace);
        // System.out.println(replace.length());
        // replace = String.format("%12s", mobile).replace(' ', '_');
        // System.out.println(replace);
        // System.out.println(replace.length());
        System.out.println(user);

        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setAge(age);
        createUserAPI.setName(name);
        createUserAPI.setMobile(mobile);
        createUserAPI.setIdentity(identity);
        System.out.println(createUserAPI);


        String s = remoteCall(createUserAPI);
        System.out.println(s);

    }

    private static String remoteCall(AbstractAPI api) throws IOException {
        //从BankAPI注解获取请求地址
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        bankAPI.url();
        StringBuffer StringBuffer = new StringBuffer();


        Arrays.stream(api.getClass().getDeclaredFields()) //获得所有字段
                .filter(field -> field.isAnnotationPresent(BankAPIField.class)) //查找标记了注解的字段
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order())) //根据注解中的order对字段排序
                .peek(field -> field.setAccessible(true)) //设置可以访问私有字段
                .forEach(field -> {
                    //获得注解
                    BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        //反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    // bankAPIField.length()
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (bankAPIField.type()) {
                        case "S": {
                            StringBuffer.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            StringBuffer.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            StringBuffer.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });

        //签名逻辑
        StringBuffer.append(DigestUtils.md2Hex(StringBuffer.toString()));
        String param = StringBuffer.toString();
        long begin = System.currentTimeMillis();

        // //发请求
        // String result = Request.Post("http://localhost:45678/reflection" + bankAPI.url())
        //         .bodyString(param, ContentType.APPLICATION_JSON)
        //         .execute().returnContent().asString();


        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return StringBuffer.toString();

    }
}