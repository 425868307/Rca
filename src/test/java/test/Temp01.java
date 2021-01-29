package test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class Temp01 {
    private static RestTemplate restTemplate = null;

    private static HttpHeaders headers = null;

    static {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            //读取超时时间
            requestFactory.setReadTimeout(8000);
            //连接超时时间
            requestFactory.setConnectTimeout(8000);
            restTemplate.setRequestFactory(requestFactory);
        }
        if (headers == null) {
            headers = new HttpHeaders();
            headers.set("version", "1.0.1");
            headers.set("Accept-Charset", "utf-8");

            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.ALL);
            headers.setAccept(acceptableMediaTypes);
        }
    }

    public static String clientMethod(String path, String head, String body) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.ALL);
        headers.setAccept(acceptableMediaTypes);
        headers.set("head", head);
        HttpEntity<String> formEntity = new HttpEntity<String>(body, headers);

        String response = restTemplate.postForObject(path, formEntity, String.class);

        return response;
    }

    public static void main(String[] args) {
        String body = "{" +
                "\"appDate\": \"20181229\"," +
                "\"appTime\": \"151300\"," +
                "\"sourceSys\": \"TEST\"," +
                "\"token\": \"123\"," +
                "\"user\": {" +
                "\"address\": \"address\"," +
                "\"agencyAgentCode\": \"aa123\"," +
                "\"agentChannel\": \"10\"," +
                "\"agentCode\": \"ac123\"," +
                "\"gender\": \"1\"," +
                "\"agentName\": \"name123\"," +
                "\"anentIsValid\": \"1\"," +
                "\"email\": \"123@123.com\"," +
                "\"orgCode\": \"13\"," +
                "\"orgName\": \"娜娜\"," +
                "\"phone\": \"12355559999\"," +
                "\"salesAgencyCode\": \"sale12\"," +
                "\"salesAgencyName\": \"sale名字\"," +
                "\"telePhone\": \"13577779999\"" +
                "}," +
                "\"userToken\": \"456\"" +
                "}";

        for (int i = 0; i < 2; i++) {


        }

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(clientMethod("http://localhost:9080/out/outweb/UserinfoPushController/userinfoPush",
                        null, body));
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(clientMethod("http://localhost:9080/out/outweb/UserinfoPushController/userinfoPush",
                        null, body));
            }
        });
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(clientMethod("http://localhost:9080/out/outweb/UserinfoPushController/userinfoPush",
                        null, body));
            }
        });
        t.start();
        t2.start();
        t3.start();
    }


}
