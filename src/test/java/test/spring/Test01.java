package test.spring;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class Test01 {

    private static RestTemplate restTemplate = new RestTemplate();

    private static HttpHeaders header = new HttpHeaders();

    static {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        header.setContentType(type);
    }

    public static void main(String[] args) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("account", "abc123111");
        String body = JSON.toJSONString(map);

        HttpEntity<String> formEntity = new HttpEntity<>(body, header);
        Object result = restTemplate.postForObject("http://localhost:8088/rca/checkAccount",
                formEntity, Object.class);
        System.out.println(result);
    }

}
