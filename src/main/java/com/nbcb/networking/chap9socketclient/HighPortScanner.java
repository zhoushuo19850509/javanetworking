package com.nbcb.networking.chap9socketclient;

import java.io.IOException;
import java.net.Socket;

/**
 * 这个代码主要是扫码某个ip上开放了哪些端口
 * 实现原理就是new 一个Socket对象实例
 * 如果端口不存在，就会抛出异常
 *
 * 这个代码用在哪里我们都知道的吧....
 */
public class HighPortScanner {
    public static void main(String[] args) {
//        String serverIP = "36.152.44.95";

        String serverIP = "127.0.0.1";
        for (int i = 1; i < 65536; i++) {
            int port = i;
            try {
                Socket socket = new Socket(serverIP, port);
                System.out.println("port [" + port + "] is available " +
                        "on serverIP: " + serverIP );
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
        System.out.println("finish scanning on server: " + serverIP);



    }
}
