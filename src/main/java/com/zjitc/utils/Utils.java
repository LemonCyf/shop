package com.zjitc.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/6 14:48
 * @description: ${description}
 */
public class Utils {

  /**
   * 随机获取一个uuid，通用唯一的识别码(Universally Unique Identifier)
   *
   * @return uuid的string类型
   */
  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**利用MD5进行加密
   * @param str  待加密的字符串
   * @return 加密后的字符串
   * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
   * @throws UnsupportedEncodingException
   */
  public static String encoderByMd5(String str)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    //确定计算方法
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    BASE64Encoder base64en = new BASE64Encoder();
    //加密后的字符串
    String result = base64en.encode(md5.digest(str.getBytes("utf-8")));
    return result;
  }
  public static String RandomEid(int length){
    //获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
      //随机字符串的随机字符库
      String KeyString = "0123456789";
      StringBuffer sb = new StringBuffer();
      int len = KeyString.length();
      for (int i = 0; i < length; i++) {
        sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
      }
      return sb.toString();

  }
}
