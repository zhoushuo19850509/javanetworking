package com.nbcb.networking.chap12nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class nonClient {

    public static void main(String[] args) {
        System.out.println("hello start client ...");

        try {
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",8088);
            SocketChannel client = SocketChannel.open(socketAddress);

            ByteBuffer buffer = ByteBuffer.allocate(74);   // buffer
            WritableByteChannel out = Channels.newChannel(System.out);

            while(client.read(buffer) != -1){
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
