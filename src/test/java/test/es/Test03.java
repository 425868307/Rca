package test.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/9/29.
 */
public class Test03 {

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = getMysqlConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from index_test");
            List<Object> lists = new ArrayList<Object>();
            Map<String, Object> map = new HashMap<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String account = rs.getString("account");
                String name = rs.getString("name");
                //打印取出来的第一条数据
                System.out.println(id + "---" + account + "----" + name);

                map.put("id", id);
                map.put("account", account);
                map.put("name", name);
                lists.add(map);
            }

            saveEs(lists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //数据导入到es
    private static void saveEs(List<Object> lists) {
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));   //连接本地的es服务器

            for (Object obj : lists) {
                Map map = (Map) obj;
                IndexResponse execIndex = client.index(new IndexRequest("yaofang", "_doc").source(map));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getMysqlConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/r-c-a?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
