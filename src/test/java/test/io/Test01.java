package test.io;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Test01 {

    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
            serverSocketChannel.socket().bind(inetSocketAddress); // 绑定到8899端口


            int messageLength = 2 + 3 + 4;

            ByteBuffer[] byteBuffers = new ByteBuffer[3];
            byteBuffers[0] = ByteBuffer.allocate(2);
            byteBuffers[1] = ByteBuffer.allocate(3);
            byteBuffers[2] = ByteBuffer.allocate(4);

            SocketChannel socketChannel = serverSocketChannel.accept();

            do {
                int byteRead = 0;
                while (byteRead < messageLength) {
                    long r = socketChannel.read(byteBuffers);
                    System.out.println("--------------r:" + r);
                    byteRead += r;

                    System.out.println("byteRead:" + byteRead);

                    Arrays.asList(byteBuffers).stream().map(buffer -> "position:" + buffer.position() + ", limit:" + buffer.limit())
                            .forEach(System.out::println);
                }

                Arrays.asList(byteBuffers).forEach(Buffer::flip);

                // 实际写的个数
                int byteWrites = 0;
                while (byteWrites < messageLength) {
                    long r = socketChannel.write(byteBuffers);
                    byteWrites += r;
                }

                Arrays.asList(byteBuffers).forEach(Buffer::clear);

                System.out.println("byteRead:" + byteRead + ", byteWrite:" + byteWrites + ", messageLength:" + messageLength);
            } while (true);
        }
    }
}
