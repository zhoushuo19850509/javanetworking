package com.nbcb.networking.chap9socketclient;


import java.io.*;
import java.net.Socket;

/**
 * 这个类主要是为了说明如何通过socket output stream
 * 把内容发送给服务端
 *
 * 这个类的功能相当于是查询功能
 * 遵循whois协议，把查询内容发送给服务端
 *
 * 后续类似的客户端场景，就能够参考这个服务
 */
public class WhoisClient {
    public static void main(String[] args) {
        String hostName = "whois.nic.fr";   // 发布whois服务的网站地址
        int port = 43;

        String name = "Amazon";   // 客户端发起的查询内容

        try {
            Socket socket = new Socket(hostName, port);

            Writer out = new OutputStreamWriter(socket.getOutputStream());

            InputStream in = new BufferedInputStream(socket.getInputStream());

            out.write(name + " ");
            out.write("\r\n");   // whois协议规定的结尾内容

            out.flush();  // 读取服务端内容之前，先要把output stream内容发送过去

            // 开始读取服务端输入流
            int c;
            StringBuilder sb = new StringBuilder();
            while( (c = in.read()) != -1 ){
                sb.append((char)c );
            }

            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
