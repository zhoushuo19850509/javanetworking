package com.nbcb.networking.chap9socketserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * DayTimeServer/DayTimeClient这一对客户端服务端
 * 主要是为了说明服务端如何通过outputstream把内容传递给客户端
 */
public class DayTimeServer {
    public static void main(String[] args) {
        int port = 9999;

        try {
            // 启动一个ServerSocket，监听来自客户端的请求
            System.out.println("start listening on port: " + port);
            ServerSocket serverSocket = new ServerSocket(port);

            // socket对象
            Socket connection = null;
            Writer out = null; // socket对应的输出流
            while(true){

                try{
                    // 一旦监听到一个来自客户端的请求
                    connection = serverSocket.accept();
                    out = new OutputStreamWriter(connection.getOutputStream());

                    Date now = new Date();
                    out.write(now.toString() + "\r\n");

                    out.flush();

                }catch (IOException e){

                }finally {
                    /**
                     * 处理完毕后，本次连接就先回收了
                     * 如果连接不回收，那是不行的哦，客户端当然也能够接收到流
                     * 但是最后那个"-1"是收不到的哦，也就是说客户端读取流的while循环一直不会退出来。
                     *
                     */
                    if(connection != null){
                        connection.close();
                    }

                    if(out != null){
                        out.close();
                    }
                }





            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
