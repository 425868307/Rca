//package test.mongoDb;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import org.bson.Document;
//
//import com.alibaba.fastjson.JSON;
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import test.pojo.Company;
//import test.unit.CommenUnit;
//
//public class Test01 {
//
//    public static void main(String[] args) {
//        MongoCollection<Document> tableHandler = getCollectionHandler();
////        insertDatas("many");
//        FindIterable<Document> result = tableHandler.find();
//        System.out.println(JSON.toJSONString(result));
//    }
//
//    private static MongoCollection<Document> getCollectionHandler(){
//     // 连接到 mongodb 服务
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//        //MongoCredential.createScramSha1Credential(userName, source, password);   //设置mongoDB的用户名&数据库&密码
//        // 连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
//        System.out.println("Connect to database successfully");
//
//        MongoCollection<Document> collection = mongoDatabase.getCollection("test01");
//        System.out.println("集合 test 选择成功");
//        return collection;
//    }
//
//  //2、创建随机对象
//    private static Random rd = new Random();
//    /**
//     * insert Datas
//     */
//    private static void insertDatas(String type){
//        Document doc = new Document();
//        Company company = new Company();
//        company.setName("31会议_"+ CommenUnit.getChineseName(4));
//        company.setAddress("上海市浦东新区_"+CommenUnit.getChineseName(5));
//        company.setPhone(String.valueOf((6+rd.nextInt(5)) << 22));
//        doc.append("name", CommenUnit.getChineseName(4)).
//        append("age", rd.nextInt(111)).
//        append("address", CommenUnit.getChineseName(7)).
//        append("gender", rd.nextBoolean()? "female":"male");
////        append("company", company);
//        List<Document> docs = new ArrayList<>();
//        docs.add(doc);
//        if("many".equals(type)) {
//            doc = new Document();
//            company = new Company();
//            company.setName("31会议_"+CommenUnit.getChineseName(4));
//            company.setAddress("上海市浦东新区_"+CommenUnit.getChineseName(5));
//            company.setPhone(String.valueOf((6+rd.nextInt(5)) << 22));
//            doc.append("name", CommenUnit.getChineseName(4)).
//            append("age", rd.nextInt(111)).
//            append("address", CommenUnit.getChineseName(7)).
//            append("gender", rd.nextBoolean()? "female":"male");
////            append("company", company);
//            doc.put("company", JSON.toJSONString(company));
//            docs.add(doc);
//        }
//        getCollectionHandler().insertMany(docs);
//
//    }
//
//}
