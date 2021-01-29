package test.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) throws IOException {
        Test01 esClient = new Test01(new String[]{});
        RestHighLevelClient client = esClient.getClient();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "yaofff");
        hashMap.put("age", "234");
        hashMap.put("address", "中国上海市");
        hashMap.put("phone", "15071243334");

        /**
         * index-api,组装index Request，设置数据，client调用执行request参数，将数据存入到es
         */
        IndexResponse execIndex = client.index(esClient.getIndexRequest("yaofang", "_doc", "1").source(hashMap));
        System.out.println(JSON.toJSONString(execIndex));


        /**
         * get-api,组装get Request，将需要查询数据的参数设置好，client调用，获取结果GETResponse
         */
        GetRequest request = new GetRequest("yaofang", "_doc", "1");
        GetResponse getResponse = client.get(request);
        System.out.println(JSON.toJSONString(getResponse));
        System.out.println(JSON.toJSONString(getResponse.getSource()));

        /**
         * exist-api，是否存在，用法与get-api类似
         */
        GetRequest exist = new GetRequest("yaofang", "_doc", "1");
        exist.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = client.exists(exist);

        /**
         * UpdateRequest-api 的必需参数如下
         * index,类型(doc),文档ID
         */
        UpdateRequest updateRequest = new UpdateRequest("yaofang", "_doc", "1");
        HashMap<String, Object> updateMap = new HashMap<>();
        updateMap.put("name", "姚放");
        updateMap.put("town", "浦东新区");
        updateRequest.doc(updateMap);
        UpdateResponse updateResponse = client.update(updateRequest);

        /**
         * Delete Request api,删除数据
         */
        DeleteRequest deleteRequest = new DeleteRequest("yaofang", "_doc", "1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(5));
        DeleteResponse deleteResponse = client.delete(deleteRequest);


        /**
         * bulk-API 混合批量操作
         */
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new UpdateRequest("posts", "doc", "2").doc(XContentType.JSON, "other", "test"));
        bulkRequest.add(new DeleteRequest("posts", "doc", "3"));

        bulkRequest.timeout(TimeValue.timeValueMinutes(2)); //设置超时时间
        bulkRequest.timeout("2m");

        BulkResponse bulkResponse = client.bulk(bulkRequest); //调用混合操作的方法

        /**
         * Search API 搜索操作
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // 添加 match_all 查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder); // 将 SearchSourceBuilder  添加到 SeachRequest 中

        SearchResponse searchResponse = client.search(searchRequest);

    }

    private String[] hostsAndPorts;

    public Test01() {

    }

    public Test01(String[] hostsAndPorts) {
        this.hostsAndPorts = hostsAndPorts;
    }

    /**
     * client获取
     *
     * @return
     */
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = null;
        List<HttpHost> httpHosts = new ArrayList<HttpHost>();
        if (hostsAndPorts.length > 0) {
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

    /**
     * indexRequest 获取
     *
     * @param index
     * @param indexType
     * @param docId
     * @return
     */
    private IndexRequest getIndexRequest(String index, String indexType, String docId) {
        IndexRequest indexRequest = null;
        if (null == index || null == indexType) {
            throw new ElasticsearchException("index or indexType must not be null");
        }
        if (null == docId) {
            indexRequest = new IndexRequest(index, indexType);
        } else {
            indexRequest = new IndexRequest(index, indexType, docId);
        }
        return indexRequest;
    }

    /**
     * 同步执行索引，同步执行，存入数据
     *
     * @param index
     * @param indexType
     * @param docId
     * @param dataMap
     * @throws IOException
     */
    public IndexResponse execIndex(String index, String indexType, String docId, Map<String, Object> dataMap)
            throws IOException {
        return getClient().index(getIndexRequest(index, indexType, docId).source(dataMap));
    }


}
