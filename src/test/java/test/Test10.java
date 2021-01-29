package test;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import sun.misc.BASE64Decoder;

public class Test10 {
    private static final String KEY = "abcdefgabcdefg12";
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }


    //自行在构造函数中赋值
    static String sessionKey = "001TqdGa1DXUqz0khXFa1Pwxo64TqdGW";

    /**
     * AES-128-CBC解密
     * 使用PKCS填充
     *
     * @param encryptedData
     * @param iv
     * @return
     * @throws Exception
     */
    public static String decryptAesPkcData(String encryptedData, String iv) throws Exception {
        //检查sessionKey的长度
        if (sessionKey.length() != 24) {
            throw new Exception("sessionId 太长");
        }
        //检查iv的长度
        if (iv.length() != 24) {
            throw new Exception("VI太长");
        }
        byte[] aesKey = Base64.decodeBase64(sessionKey);
        byte[] aesIV = Base64.decodeBase64(iv);
        byte[] aesCipher = Base64.decodeBase64(encryptedData);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        Key sKeySpec = new SecretKeySpec(aesKey, "AES");
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(aesIV));
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
        return new String(cipher.doFinal(aesCipher), "utf-8");
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {

        String encodeStr = "IDImfNy7Y7egYs3kQLTgT6Z1VAav3meTnijvFjkD0rvbvQpy4zEXpinROUW1sIMvB5XeupqQHpgfVBlaYq6oCyj2AJK3Mr2O3iV8Vyp0NvN2vNb868AMIZW7II3RaK1xGCqJeIODSJ6z/qcQOcNsyhc6PKRRew3DyAQ8IKndXONtkEGkq9N3FZdxslvwQ648tD8wPFwpxe3EBnKv4FIUnxpow20iZmAyNHk2GGley9DA2zT8UAM0nqYIB6yogw5FGovt7U2VTyQhSme4WOyE949owFRmghdG4wwbZCyoyK1Zy7kCyc9vC3hzk19LTlefgRs3+nMKzjyWsLEf4es5WN4mJ/eYC7ybbLeC3xN7JdDrLywT++iXKuuQxfZmFrljGsic2Dnt6B3ITXpbeQ87YnrplY1gtscaXjtS7c0q7KUwUDO4JYA1ASs77Jcb4PgYrbfmOAHRIXoGP+Rmv2g73Q==";
        String myKey = "QrPcF1IstTk1GaDTF5jgaw==";

//        String content = "Test String么么哒";  //0gqIDaFNAAmwvv3tKsFOFf9P9m/6MWlmtB8SspgxqpWKYnELb/lXkyXm7P4sMf3e
//        System.out.println("加密前：" + content);  
//
//        System.out.println("加密密钥和解密密钥：" + KEY);  
//
//        String encrypt = aesEncrypt(content, KEY);  
//        System.out.println(encrypt.length()+":加密后：" + encrypt);  

        System.out.println(new String(Base64.decodeBase64(myKey)));


        String decryptAesPkcData = decryptAesPkcData(encodeStr, myKey);
        System.out.println(decryptAesPkcData);


        String decrypt = aesDecrypt(encodeStr, myKey);
        System.out.println("解密后：" + decrypt);
    }
}