package com.yaof.io;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/** 
    * Filename:     SimpleClientHandler.java 
    * @Copyright:   Copyright (c)2017 
    * @Company:     jd 
    * @author:      jason 
    * @function:
    * @version:     1.0 
    * @Create at:   2019年5月25日 下午11:34:40 
    */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	        if (msg instanceof ByteBuf) {
	            String value = ((ByteBuf) msg).toString(Charset.defaultCharset());
	            System.out.println("服务器端返回的数据:" + value);
	        }
	         
	        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
	        ctx.channel().attr(key).set("客户端处理完毕");

	        //把客户端的通道关闭
	        ctx.channel().close();
	    }
}
