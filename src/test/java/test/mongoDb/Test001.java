//package test.mongoDb;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.bson.BsonDocument;
//import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.conversions.Bson;
//
//import com.alibaba.fastjson.JSON;
//import com.mongodb.BasicDBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//public class Test001 {
//
//	public static void main(String[] args) {
//		try{
//	         // 连接到 mongodb 服务
//	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//	         //MongoCredential.createScramSha1Credential(userName, source, password);	//设置mongoDB的用户名&数据库&密码
//	         // 连接到数据库
//	         MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
//	         System.out.println("Connect to database successfully");
//
//	         MongoCollection<Document> collection = mongoDatabase.getCollection("test01");
//	         System.out.println("集合 test 选择成功");
//	         //插入文档
//	         /**
//	         * 1. 创建文档 org.bson.Document 参数为key-value的格式
//	         * 2. 创建文档集合List<Document>
//	         * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
//	         * */
//	         Document document = new Document("title", "mysql").
//	         append("description", "database").
//	         append("likes", 123).
//	         append("by", "id");
//	         List<Document> documents = new ArrayList<Document>();
//	         documents.add(document);
////	         collection.insertMany(documents);
////	         System.out.println("文档插入成功");
//			System.out.println("文旦插入成功。。。");
//
//			BasicDBObject bson = new BasicDBObject();
//			bson.append("name", "wangwu");
//			bson.containsField("name");
//			bson.containsField("age");
//			FindIterable<Document> result = collection.find(bson);
//			System.out.println(JSON.toJSONString(result));
//
//		}catch(Exception e){
//	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	      }
//	}
//
//
//	private static MongoDatabase getMongoCollection(){
//		try {
//            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
//            //ServerAddress()两个参数分别为 服务器地址 和 端口
//            ServerAddress serverAddress = new ServerAddress("localhost",27017);
//            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
//            addrs.add(serverAddress);
//
//            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//            credentials.add(credential);
//
//            //通过连接认证获取MongoDB连接
//            MongoClient mongoClient = new MongoClient(addrs,credentials);
//
//            //连接到数据库
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
//
//            System.out.println("Connect to database successfully");
//            return mongoDatabase;
//        } catch (Exception e) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//		return null;
//	}
//
//}
