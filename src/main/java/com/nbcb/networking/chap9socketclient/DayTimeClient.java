package com.nbcb.networking.chap9socketclient;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 这个例子是socket client示例
 * 从一个官网地址(time.nist.gov)读取时间(应该是美国时间)
 * 说明如何通过读取inputstream的方式，从socket读取返回流内容
 */
public class DayTimeClient {
    public static void main(String[] args) {
        String hostName = "time.nist.gov";
        int port = 13;

        try {
            Socket socket = new Socket(hostName, port);

            InputStream in = socket.getInputStream();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            int c ;

            while( (c = in.read()) != -1){
                sb.append((char)c);
                sb2.append(c).append(",");
            }
            System.out.println("daytime from " + hostName + " is: " + sb.toString());
            System.out.println(sb2.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
