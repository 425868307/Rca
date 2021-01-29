package test.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/9/27.
 * elasticSearch,es的简单使用实例
 */
public class Test02 {

    public static void main(String[] args) {
        QueryBuilder termQuery = QueryBuilders.termsQuery("name", "yaofff", "saner");   //es Java查询 name为‘yaofff’或‘saner’的所有数据
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(termQuery);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.sort("age");    //排序方式
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);  //文档名称
        searchRequest.types("users");

        try {
//		    GetRequest getRequest = new GetRequest("users","_doc","1");
//		    getRequest.type("_doc");
//			GetResponse resp = getClient(null).get(getRequest);
//			System.out.println(JSON.toJSONString(resp));
//			System.out.println(resp.getSource());

            SearchResponse search = getClient(null).search(searchRequest);
            System.out.println(JSON.toJSONString(search));

            SearchHit[] hits = search.getHits().getHits();
            System.out.println(JSON.toJSONString(hits));

            Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
            System.out.println(JSON.toJSONString(sourceAsMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * client获取
     *
     * @return
     */
    public static RestHighLevelClient getClient(List<String> hostsAndPorts) {
        RestHighLevelClient client = null;
        if (hostsAndPorts != null && hostsAndPorts.size() == 0) {
            List<HttpHost> httpHosts = new ArrayList<HttpHost>();
            for (String hostsAndPort : hostsAndPorts) {
                String[] hp = hostsAndPort.split(":");
                httpHosts.add(new HttpHost(hp[0], Integer.valueOf(hp[1]), "http"));
            }
            client = new RestHighLevelClient(RestClient.builder(httpHosts.toArray(new HttpHost[0])));
        } else {
//			client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));   //连接本地的es服务器
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("106.75.34.53", 9200, "http")));
        }
        return client;
    }
}
