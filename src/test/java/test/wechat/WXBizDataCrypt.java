package test.wechat;

/*
 * �ļ�����WXBizDataCrypt.java
 * ��Ȩ��
 * ������
 * �޸��ˣ�Awoke
 * �޸�ʱ�䣺2018-1-24
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * ��΢��С�����û��������ݵĽ���ʾ������.
 *
 * @author Awoke
 * @version 2018-1-24
 * @see WXBizDataCrypt
 */
public class WXBizDataCrypt {

    private String appid;

    private String sessionKey;

    public WXBizDataCrypt(String appid, String sessionKey) {
        this.appid = appid;
        this.sessionKey = sessionKey;
    }

    /**
     * �������ݵ���ʵ�ԣ����һ�ȡ���ܺ������.
     *
     * @param encryptedData string ���ܵ��û�����
     * @param iv            string ���û�����һͬ���صĳ�ʼ����
     * @return data string ���ܺ��ԭ��
     */
    public String decryptData(String encryptedData, String iv) {
        if (sessionKey.length() != 24) {
            return "ErrorCode::$IllegalAesKey;";
        }
        // �Գƽ�����Կ aeskey = Base64_Decode(session_key), aeskey ��16�ֽڡ�
        byte[] aesKey = Base64.decodeBase64(sessionKey);

        if (sessionKey.length() != 24) {
            return "ErrorCode::$IllegalIv;";
        }
        // �Գƽ����㷨��ʼ���� ΪBase64_Decode(iv)������iv�����ݽӿڷ��ء�
        byte[] aesIV = Base64.decodeBase64(iv);

        // �Գƽ��ܵ�Ŀ������Ϊ Base64_Decode(encryptedData)
        byte[] aesCipher = Base64.decodeBase64(encryptedData);

        Map<String, String> map = new HashMap<>();

        try {
            byte[] resultByte = WechatUtil.decrypt(aesCipher, aesKey, aesIV);

            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                map.put("code", "0000");
                map.put("msg", "succeed");
                map.put("userInfo", userInfo);

                // watermark����˵����
                // ����  ����  ˵��
                // watermark   OBJECT  ����ˮӡ
                // appid   String  �������ݹ���appid�������߿�У��˲���������appid�Ƿ�һ��
                // timestamp   DateInt �������ݻ�ȡ��ʱ���, �����߿�����������ʱЧ��У��'

                // ����΢�Ž��飺�������ݹ���appid�������߿�У��˲���������appid�Ƿ�һ��
                // if decrypted['watermark']['appid'] != self.appId:
                JSONObject jsons = JSON.parseObject(userInfo);
                String id = jsons.getJSONObject("watermark").getString("appid");
                if (!StringUtils.equals(id, appid)) {
                    return "ErrorCode::$IllegalBuffer;";
                }
            } else {
                map.put("status", "1000");
                map.put("msg", "false");
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * @param args
     * @see
     */
    public static void main(String[] args) {
        String appId = "wx006505cfb07a120e";
        String sessionKey = "Dn1fiCjJ0k0p6ijxcSp7sQ==";
        String encryptedData = "ECrsjP/V9KtP0k/WeleibC87cNLZdRT97EtCOianzFggPK0GXEOaSXRdVNjD5pr6uwUqwNmMxmQZ5FrUHaqf+NKHwSY+rVw0svV50NKS5F4hJReYN5eTP6MFz3g9T9g2wV3FnP0eaPMn8UKCjPfN+Q6Qa63nN+fJfITMYHa0/dOjCEi1RZirqOFZl0UsiiAWSxbd0fhwINFJU6xnlcn9VqgUIyEOE/2EvEPHjJ6WukoQp7pTqCLu9mTD/ksErbMMtb1xK5hG3ZbTmBwog8Rayy5mfjWyxFPD1ab/VH0heDUrgekcp7B9OaCxotzU4flnVRcOrzTQCR0hBgue5BFbrNVb1Lxj2K0Y0UhvP/Jg2dvk/CAIK8GR4VmQ7v4d17SSjITig51IdkOTAois+HZ7Y4fXPR317EJ1HGa7zioDlEi0y7+GZs1CtxBbfKH+3c/C5bEwjAA9yujDH6FVE+KDwQ==";
        String iv = "WaVkE0MVhEMfoKo/V733lQ==";

        WXBizDataCrypt biz = new WXBizDataCrypt(appId, sessionKey);

        System.out.println(biz.decryptData(encryptedData, iv));

    }
}
