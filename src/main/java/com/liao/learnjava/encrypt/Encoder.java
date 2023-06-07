package com.liao.learnjava.encrypt;

import com.byd.tool.PrintUtil;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/6/2
 **/
public class Encoder {
    
    @Test
    public void test1ForUrl() {
        // 和标准的URL编码稍有不同，URLEncoder把空格字符编码成+，而现在的URL编码标准要求空格被编码为%20
        String s = "中国! 123";
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8);
        PrintUtil.println(encode);
        String encode2 = encode.replaceAll("\\+", "%20");
        PrintUtil.println(encode2);
        
        String decode1 = URLDecoder.decode(encode, Charset.defaultCharset());
        PrintUtil.println(decode1);
        String decode2 = URLDecoder.decode(encode2, Charset.defaultCharset());
        PrintUtil.println(decode2);
    }
    
    
    @Test
    public void test1ForBase64() {
        // 0100-0001 0000 0000 0000 0000
        // 0100-0000 0100-0000 0000-0000 0000-0000
        
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad, (byte) 0x41};
        PrintUtil.println(input);
        String s = new String(input);
        PrintUtil.println(s);
        
        byte[] encode = Base64.getEncoder().encode(input);
        PrintUtil.println(new String(encode));
        
        String encode1 = Base64.getEncoder().encodeToString(input);
        PrintUtil.println(encode1);
        
        // 0001 1011  0xe4 -->  1B
        // 1110 0100 --- 取反+1--> 0001 1011 + 1 =  0001 1100 --> -28
        byte[] decode = Base64.getDecoder().decode(encode);
        PrintUtil.println(Arrays.toString(decode));
        PrintUtil.println(new String(decode));
        
    }
    
    @Test
    public void testEncoder2() {
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x41};
        String b64encoded = Base64.getEncoder().encodeToString(input);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);
        byte[] output = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output));
    }
    
    @Test
    public void testEncode3() {
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21};
        
        input = new byte[]{(byte) 0x01, (byte) 0x02, (byte) 0x7f, (byte) 0x00};
        PrintUtil.println(new String(input));
        String b64encoded = Base64.getUrlEncoder().encodeToString(input);
        System.out.println(b64encoded);
        String b64encoded2 = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded2);
        
        byte[] output = Base64.getUrlDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output) + " " + new String(output));
        //output = Base64.getDecoder().decode(b64encoded);
        //System.out.println(Arrays.toString(output) + " " + new String(output));
        
    }
    
    
}
