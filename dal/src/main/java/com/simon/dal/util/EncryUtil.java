package com.simon.dal.util;

/**
 * @Author simon.feng
 * @Date Created in 17:59 2018/12/13/013
 * @Modificd
 */
public class EncryUtil {

    /**
     * 哈希加密
     *
     * @param strSrc
     * 需要加密的字符串
     * @param encName
     * 需要采用的加密方式，如：SHA-1,SHA-256,SHA-256等等
     * @return
     */

    private static final Logger log = LoggerFactory.getLogger(EncryptUtil.class);

    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 将二进制数据编码为BASE64字符串
     *
     * @param binaryData
     * @return
     */
    public static String encode(byte[] binaryData) {
        try {
            return new String(Base64.encodeBase64(binaryData), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将BASE64字符串恢复为二进制数据
     *
     * @param base64String
     * @return
     */
    public static byte[] decode(String base64String) {
        try {
            return Base64.decodeBase64(base64String.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * MD5加密
     *
     * @param message 要进行MD5加密的字符串
     * @return 加密结果为32位字符串
     */
    public static String getMD5(String message) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(message.getBytes("UTF-8"));

            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return md5StrBuff.toString().toUpperCase();//字母大写
    }


    /**
     * MD5加密,加密结果采用Base64进行编码
     *
     * @param message 要进行MD5加密的字符串
     * @return
     */
    public static String getMD5ByBase64(String message) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte md5[] = md.digest(message.getBytes());
            BASE64Encoder base64 = new BASE64Encoder();
            return base64.encode(md5);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}

}
