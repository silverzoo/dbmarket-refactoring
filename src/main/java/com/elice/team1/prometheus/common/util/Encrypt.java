package com.elice.team1.prometheus.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    // Static으로 해놓을테니 필요하면 쓰세요!
    public static String md5(String message) {
        String encData = "";
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            // parameter String을 Byte화
            byte[] bytes = message.getBytes();
            // byte화된 parameter를 md5 지정된 byte 배열로 업데이트
            md.update(bytes);
            // digest() - 데이터 해시 처리
            byte[] digest = md.digest();
            // 16진수 변환
            for (int i = 0; i < digest.length; i++) {
                encData += Integer.toHexString(digest[i] & 0xff);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encData;
    }

}
