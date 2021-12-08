package com.chuancheng.controller;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time = timestamp.getTime();
        String sign = getSign(time,"suiteTicket","suiteSecret");
        System.out.println(sign);

    }

    public static String getSign(long timestamp,String suiteTicket,String suiteSecret)throws Exception{
        String stringToSign = timestamp+"\n"+suiteTicket;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(suiteSecret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = new String(Base64.encodeBase64(signData));
        return sign;
    }
}
