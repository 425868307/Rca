package com.yaof.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test01 {
	
	static{
		System.out.println("test01 static");
	}

	public static void main(String[] args) throws IOException {
		
		
		Supplier<String> i = () -> "Car";
	       Consumer<String> c = x -> System.out.print(x.toLowerCase());
	       Consumer<String> d = x -> System.out.print(x.toUpperCase());
	       c.andThen(d).accept(i.get());
	       System.out.println();

		Selector selector = Selector.open();
	    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
	    serverSocketChannel.socket().bind(new InetSocketAddress(8888));//端口
	    serverSocketChannel.configureBlocking(false);
	    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

	    while (true) {
	        int n = selector.select(); // Block
	        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
	        while (it.hasNext()) {
	            SelectionKey key = it.next();
	            if (key.isAcceptable()) {
	                ServerSocketChannel server = (ServerSocketChannel) key.channel();
	                SocketChannel channel = server.accept();
	                if (channel != null) {
	                    channel.configureBlocking(false);
	                    channel.register(selector, SelectionKey.OP_READ);
	                    onAccept(channel);
	                }
	            }
	            if (key.isReadable()) {
	                SocketChannel socketChannel = (SocketChannel) key.channel();
	                onRead(socketChannel);
	            }
	            it.remove();
	        }
	    }
	}
	
	
	private static void onRead(SocketChannel socketChannel) throws IOException {
	    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	    int count;
	    while ((count = socketChannel.read(buffer)) > 0) {
	        buffer.flip();
	        while (buffer.hasRemaining()) {
	            socketChannel.write(buffer);
	        }
	        buffer.clear();
	    }

	    if (count < 0) {
	        socketChannel.close();
	    }
	}

	private static void onAccept(SocketChannel channel) throws IOException {
	    System.out.println(channel.socket().getInetAddress() + "/" + channel.socket().getPort());
	    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	    buffer.put("hello\n".getBytes());
	    buffer.flip();
	    channel.write(buffer);
	}

}
