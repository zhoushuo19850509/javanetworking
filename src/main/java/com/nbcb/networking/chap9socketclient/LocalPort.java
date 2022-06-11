package com.nbcb.networking.chap9socketclient;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * 这个代码主要是打印local port
 * localport是啥意思呢？客户端和服务端建立一个连接后，
 * 除了对端port(服务端发布的固定端口)，还有一个操作系统分配给客户端的本地端口
 * 从代码可以看到，这个本地端口是随机生成的。
 *
 */
public class LocalPort {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10000; i++) {
                Socket socket = new Socket("127.0.0.1",9099);
                int localPort = socket.getLocalPort();
                String localHost = socket.getLocalAddress().getHostAddress();
                System.out.println("localPort: " + localPort);
                System.out.println("localHost: " + localHost);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
