package com.sange.base.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * 字节数组 与 字符串相互转化类
 *
 * @author ssq
 */
public class ByteUtils {

    /**
     * 字符串转化成为16进制字符串
     */
    public static String strTo16(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str.append(s4);
        }
        return str.toString();
    }
    /**
     * 16进制转换成为string类型字符串
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, StandardCharsets.UTF_8);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
    /**
     * 向串口发送数据转为字节数组
     */
    public static byte[] hex2byte(String hex) {
//        String digital = "0123456789ABCDEF";
        String digital = "0123456789abcdef";
        String hex1 = hex.replace(" ", "");
        char[] hex2char = hex1.toCharArray();
        byte[] bytes = new byte[hex1.length() / 2];
        byte temp;
        for (int p = 0; p < bytes.length; p++) {
            temp = (byte) (digital.indexOf(hex2char[2 * p]) * 16);
            temp += digital.indexOf(hex2char[2 * p + 1]);
            bytes[p] = (byte) (temp & 0xff);
        }
        return bytes;
    }
    /**
     * 接收到的字节数组转换16进制字符串
     */
    public static String bytes2HexString(byte[] b, int size) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * 接收到的字节数组转换16进制字符串
     */
    public static String byteToStr(byte[] b, int size) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }
    /**
     * BASE64码解密成图片
     */
    public static Bitmap Base64ToImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        Bitmap bitmap = null;
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
            return bitmap;
        } catch (Exception e) {
            LogUtils.INSTANCE.e("解析异常");
            return bitmap;
        }
    }
    /**
     * 将图片转换为base64加密数据
     */
    public static String ImageToBase64(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            LogUtils.INSTANCE.e("加密异常");
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    /**
     * 计算CRC16校验码
     * 逐个求和
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static String getCRC_16(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        int length = Integer.toHexString(CRC).toUpperCase().length();
        if (length == 2) {
            return byteToStr(bytes, bytes.length) + "00" + Integer.toHexString(CRC).toUpperCase();
        } else if (length == 3) {
            return byteToStr(bytes, bytes.length) + "0" + Integer.toHexString(CRC).toUpperCase();
        }
        return byteToStr(bytes, bytes.length) + Integer.toHexString(CRC).toUpperCase();
    }

    /**
     * 指令校验和,并取出后两位字节
     * */
    public static String getSum16(byte[] msg, int length) {
        long mSum = 0;
        byte[] mByte = new byte[length];

        /* 逐Byte添加位数和 */
        for (byte byteMsg : msg) {
            long mNum = ((long) byteMsg >= 0) ? (long) byteMsg : ((long) byteMsg + 256);
            mSum += mNum;
        } /* end of for (byte byteMsg : msg) */

        /* 位数和转化为Byte数组 */
        for (int liv_Count = 0; liv_Count < length; liv_Count++) {
            mByte[length - liv_Count - 1] = (byte) (mSum >> (liv_Count * 8) & 0xff);
        } /* end of for (int liv_Count = 0; liv_Count < length; liv_Count++) */
        return byteToStr(msg, length) + byteToStr(mByte, mByte.length).substring(byteToStr(mByte, mByte.length).length() - 4);
    }
}

