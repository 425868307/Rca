package test.wechat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test01 {

    public static void main(String[] args) {
        Map aa = decodeUserInfo("yOtpuoMQocHO5lSr8qpGbSqWxAUexTBSc355k45r1ws8MlTrKS2d6DyUekC1vKM3RbEmbnmvfSGZbtJX4P2dmgXdFW7y9q4eAKeVjgfBp9EnLtKIDD6P9w6I86idw9O+UOjaeBIlC377h9wxcv0yXb+htm4EYkFmdZv8AVkw4eCyTtgd5OEOZdC4msYgkovXNJ/XxAXUvhqJQTBg2QKy/pt8b0gmVuxF2c0Om8fzDlGsL1nL+MJ9nCwSKu6A1Xa7pWVdLLIX9DFpUVoj2IQJGFegrGE8NdY/zUkh2eHABjFXDsuZshC9IfVhfuE5tomZezvUR842fRIxOhSjH36gaNya5txIuq7vkzr8hExvi0GnNVZx1weNiFjO8sH0cbN7NIV/QcwD6FjXofiiqmpoBt5ad0UUIej4RA8kX0hChrqdVsN3tlpb0pb3oAVqV43hmn1PfJnAteY7+iNBRgrieA==",
                "88R43dGNkT3bigwcVuzgfw==",
                "091XSB1w3mIbKU2iV94w3N4mxX1XSB1U");
        System.out.println(JSON.toJSONString(aa));
    }

    /**
     * @param encryptedData 明文,加密数据
     * @param iv            加密算法的初始向量
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return
     * @Title: decodeUserInfo
     * @author：lizheng
     * @date：2018年3月25日
     * @Description: 解密用户敏感数据
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public static Map decodeUserInfo(String encryptedData, String iv, String code) {

        Map map = new HashMap();

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wxaf8a1f052595a82b";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "48dd54bc2223defb1fc61dc8859c704e";
//		// 小程序唯一标识 (在微信小程序管理后台获取)
//		String wxspAppid = "wx006505cfb07a120e";
//		// 小程序的 app secret (在微信小程序管理后台获取)
//		String wxspSecret = "7270c02f831bd66b2245e2d0f03eb166";
        // 授权（必填）
        String grant_type = "authorization_code";

        //1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid

        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        // 2、对encryptedData加密数据进行AES解密
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;

                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
            } else {
                map.put("status", 0);
                map.put("msg", "解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
