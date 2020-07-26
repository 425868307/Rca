package com.yaof.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ShowMeBug02 {
	private static boolean enable = true;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// MsgNotice.sendMessage("http://www.baidu.com", "json");
		System.out.println("MsgNotice是一个发送数据的类，运行在多线程环境中");
		System.out.println("类MsgNotice实现是否有问题（如有问题请修正并说明）");
		System.out.println("用一段语言描述这样设计的优点是什么？缺点是什么？");
		System.out.println("改动点有：1.completionService改成静态的了;2.callable执行完的结果用一个map存储(具体用什么根据实际业务)，"
				+ "需要使用的时候取出，如果不需要返回值，可以用runnable"
				+ "3.connection.setDoOutput(true);远程调用");
		System.out.println("这样的设计最大的优点是多线程运行，能提高性能。"
				+ "缺点有：1.线程池的创建不要用Executors.newFixedThreadPool(10)的方法；"
				+ "2.核心线程配置到配置文件，设置值与服务器CPU个数相关；"
				+ "3.远程调用容易失败，需要对消息进行处理。");
		MsgNotice.sendMessage("http://localhost:8083/mg/test/test", "sadadsa");
		System.out.println(MsgNotice.result.get("http://localhost:8083/mg/test/testsadadsa").get());
	}

	private static class MsgNotice {

		private static final ExecutorService notifiedService = Executors.newFixedThreadPool(10);
		private static final CompletionService<String> completionService = new ExecutorCompletionService<>(notifiedService);
		public static Map<String, Future<String>> result = new HashMap<>();

		public static void sendMessage(String url, String message) {
			if (!enable) {
				return;
			}
			result.put(url+message, completionService.submit(() -> {
				String result = null;
//				try {
//					result = doPost(url, message);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				throw new Exception();
//				return result;
			}));

		}

		private static String doPost(String httpUrl, String param) {

			HttpURLConnection connection = null;
			InputStream is = null;
			OutputStream os = null;
			BufferedReader br = null;
			String result = null;
			try {
				URL url = new URL(httpUrl);
				// 通过远程url连接对象打开连接
				connection = (HttpURLConnection) url.openConnection();
				// 设置连接请求方式
				connection.setRequestMethod("POST");
				// 设置传入参数
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setDoOutput(true);
				// 通过连接对象获取一个输出流
				os = connection.getOutputStream();
				// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
				os.write(param.getBytes());
				// 通过连接对象获取一个输入流，读取数据
				if (connection.getResponseCode() == 200) {
					is = connection.getInputStream();
					br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

					StringBuffer sbf = new StringBuffer();
					String temp = null;
					while ((temp = br.readLine()) != null) {
						sbf.append(temp);
						sbf.append("\r\n");
					}
					result = sbf.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭资源
				if (null != br) {
					try {
						br.close();
					} catch (IOException e) {
					}
				}
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
					}
				}
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
					}
				}
				if (null != connection) {
					connection.disconnect();
				}
			}
			return result;
		}
	}

}
