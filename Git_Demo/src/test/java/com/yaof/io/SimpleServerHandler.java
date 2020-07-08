package com.yaof.io;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
    * Filename:     SimpleServerHandler.java 
    * @Copyright:   Copyright (c)2017 
    * @Company:     jd 
    * @author:      jason 
    * @function:
    * @version:     1.0 
    * @Create at:   2019年5月25日 下午11:25:26 
    */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter{

	/**
     * 读取客户端通道的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //可以在这里面写一套类似SpringMVC的框架
        //让SimpleServerHandler不跟任何业务有关，可以封装一套框架
        if(msg instanceof ByteBuf){
            System.out.println(((ByteBuf)msg).toString(Charset.defaultCharset()));
        }
        
        //业务逻辑代码处理框架。。。
        
        //返回给客户端的数据，告诉我已经读到你的数据了
        String result = "hello client ";
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(result.getBytes());
        ctx.channel().writeAndFlush(buf);
        
        result = "dsajiodjiasd";
        ByteBuf bufx = Unpooled.buffer();
        bufx.writeBytes(result.getBytes());
        ctx.channel().writeAndFlush(bufx);
        
        ByteBuf buf2 = Unpooled.buffer();
        buf2.writeBytes("\r\n".getBytes());
        ctx.channel().writeAndFlush(buf2);
        System.out.println("==========");
    }
}
