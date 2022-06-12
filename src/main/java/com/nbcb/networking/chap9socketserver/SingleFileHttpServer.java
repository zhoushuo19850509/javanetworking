package com.nbcb.networking.chap9socketserver;


import com.nbcb.networking.util.FileUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SingleFileHttpServer顾名思义，就是发布一下单文件下载的http服务
 * 实现原理，就是启动一个单独的线程，
 * 通过ServerSocket对方发布socket监听，监听来自客户端的Http请求
 * 然后把本地指定的一个文件返回给客户端
 *
 * 备注：这虽然是一个socket server，但是能够遵循http协议。
 *
 * 这个例子非常形象说明了如何通过java socket开发一个http server
 * 例子中是"text/plain" 说明是普通文件
 * 只要改为"text/html"，就能发布html页面了。那也就是一个http server了
 *
 */
public class SingleFileHttpServer extends Thread{


    private int port;  // SingleFileHttpServer监听的端口
    private byte[] content; // SingleFileHttpServer发布的文件流，byte[]形式
    private byte[] header; // SingleFileHttpServer返回给客户端的http header
    private String contentType;
    private String encoding;

    /**
     * constructor
     * @param content 要返回给客户端的file content
     * @param port 端口
     * @param contentType content类型
     * @param encoding 编码
     */
    public SingleFileHttpServer(int port, byte[] content, String contentType, String encoding) {
        this.port = port;
        this.content = content;
        this.contentType = contentType;
        this.encoding = encoding;

        this.header = ("HTTP/1.0 200\r\n" +
                "Server: OneFile 1.0\r\n" +
                "Content-length: " + this.content.length + "\r\n" +
                "Content-type: " + contentType + "\r\n\r\n")
                .getBytes();
    }

    /**
     * 主线程做的事情，就是启动一个ServerSocket，监听来自客户端的请求
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            System.out.println("SingleFileHttpServer start listening on port : " + port);

            Socket connection = null;

            try{
                while(true){
                    connection = serverSocket.accept();
                    System.out.println("accept a connectiont from client : " +
                            connection.getInetAddress().getHostAddress());

                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    // 先读取来自客户端的inputstream
                    int c;
                    StringBuilder sb = new StringBuilder();
                    while(true){
                        c = in.read();
                        if(c == -1 || c == '\r' || c == '\n'){
                            break;
                        }
                        sb.append((char)c);
                        System.out.print((char)c);
                    }
                    String request = sb.toString();
                    System.out.println("request from client : " + request);

                    /**
                     * 如果来自客户端的inputstream包含"HTTP"，说明客户端是按照HTTP协议来请求的，
                     * 那么服务端要返回对应的HTTP格式的response
                     */
                    if(request.contains("HTTP")){
                        out.write(this.header);
                        System.out.println("finish write the http header... ");
                    }
                    // 返回文件byte[]
                    out.write(this.content);
                    out.flush();
                    System.out.println("finish response to client...");

                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                // 请求结束后关闭连接
                if(null != connection){
                    connection.close();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        // 读取本地一个文件
        String filePath = "/Users/zhoushuo/Downloads/delete/a.html";
        byte[] content = FileUtil.toByte(filePath);
        String encoding = "ASCII";
        String contentType = "text/html";
        int port = 9999;

        Thread t = new SingleFileHttpServer(port, content, contentType, encoding);
        t.start();

    }


}
