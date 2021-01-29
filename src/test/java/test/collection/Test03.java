package test.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import test.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test03 {

    private static ObjectMapper MAPPER = null;

    static {
        if (MAPPER == null) {
            MAPPER = new ObjectMapper();
//            MAPPER.setDateFormat(Constants.F_DATE_TIME);
            // 设置将对象转换成JSON字符串时候:包含的属性不能为空或"";
            // Include.Include.ALWAYS 默认
            // Include.NON_DEFAULT 属性为默认值不序列化
            // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化
            // Include.NON_NULL 属性为NULL 不序列化
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//            MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            // mapper.setSerializationInclusion(Include.NON_NULL);
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("aa", "bbb");
        map.forEach((a, b) -> {
            System.out.println(a.getClass());
            System.out.println(b.getClass());
        });

        User user = new User();
        user.setUserName("yaof");
        user.setUserPhone("12354353");
        user.setUserPhone(null);
        Map map1 = MAPPER.convertValue(user, Map.class);
        Set set = map1.keySet();
        for (Object ss : set) {
            System.out.println(ss.toString());
        }

    }
}
