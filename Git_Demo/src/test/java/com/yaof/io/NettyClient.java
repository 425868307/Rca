package com.yaof.io;

import com.alibaba.fastjson.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;

/** 
    * Filename:     NettyClient.java 
    * @Copyright:   Copyright (c)2017 
    * @Company:     jd 
    * @author:      jason 
    * @function:
    * @version:     1.0 
    * @Create at:   2019年5月25日 下午11:33:29 
    */
public class NettyClient {

	public static void main(String[] args) throws InterruptedException {
        //1、创建客户端启动组件Bootstrap
        Bootstrap client = new Bootstrap();
        
        //2、定义线程组组件，处理读写和链接事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group );
        
        //3、客户端启动组件绑定客户端通道组件NioSocketChannel
        client.channel(NioSocketChannel.class);
        
        //4、 启动组件给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new ChannelInitializer<NioSocketChannel>() {  //通道是NioSocketChannel
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                //字符串编码器，一定要加在SimpleClientHandler 的上面
            	//7、管道组件实现相关责任链的功能
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(   
                        Integer.MAX_VALUE, Delimiters.lineDelimiter()[0])); 
                //5、实现处理具体业务逻辑的客户端处理器组件SimpleClientHandler
                //6、管道组件添加处理器组件SimpleClientHandler
                ch.pipeline().addLast(new SimpleClientHandler());
            }
        });
        
        //8、连接服务端、向服务端发送数据
        //9、处理服务端返回的数据
        ChannelFuture future = client.connect("localhost", 8080).sync();
        
        //发送数据给服务器
        User user = new User();
        user.setAge(12);
        user.setId(1);
        user.setName("sssss");
        future.channel().writeAndFlush(JSONObject.toJSONString(user)+"\r\n");
        
        future.channel().writeAndFlush("你好，我是客户端\r\n");
        
//        for(int i=0;i<5;i++){
//            String msg = "ssss"+i+"\r\n";
//            future.channel().writeAndFlush(msg);
//        }
        
        //当通道关闭了，就继续往下走
        future.channel().closeFuture().sync();
        
        //接收服务端返回的数据
//        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
//        Object result = future.channel().attr(key).get();
//        System.out.println(result.toString());
    }

	
}
