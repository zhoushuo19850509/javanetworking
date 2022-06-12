package com.nbcb.networking.chap9socketserver;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个文件模拟一个server，这个server启动两个线程
 * 线程1 能够读取来自客户端的信息；
 * 线程2 能够把服务端的内容发送给客户端；
 */
public class ClientTester {
    public static void main(String[] args) {

        int port = 9999;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server start listen on port : " + port);

            Socket connection = null;

            while(true){

                connection = serverSocket.accept();

                try {
                    // 启动线程1 InputThread 读取来自客户端的流
                    Thread thread1 = new InputThread(connection.getInputStream());
                    thread1.start();

                    // 启动线程2 OutputThread 把内容输出给客户端
                    Thread thread2 = new OutputThread(connection.getOutputStream());
                    thread2.start();

                    thread1.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(null != connection){
                        connection.close();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

/**
 * 这个单独的线程类，主要是根据inputstream读取来自客户端的请求
 */
class InputThread extends Thread{

    private InputStream in ;

    public InputThread(InputStream in){
        this.in = in;
    }

    @Override
    public void run() {
        System.out.println("start InputThread ...");

        try{
            int c;
            // 只要客户端不退出，就一直打印客户端传过来的数据
            while( (c = in.read()) != -1){
                System.out.write(c);
            }

        }catch (IOException e){
            e.printStackTrace();

        }finally {
            System.out.println("finish InputThread ... ");
        }

    }
}

/**
 * 线程2 通过outputstream的方式，把内容传递给客户端
 */
class OutputThread extends Thread{

    private OutputStream out;

    public OutputThread(OutputStream out){
        this.out = out;
    }

    @Override
    public void run() {
        System.out.println("start OutputThread ...");

        Writer writer = new OutputStreamWriter(this.out);
        String line = "";
        try {
            // 读取System.in的内容
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                line = in.readLine();
                if(line.equals(".")){
                    break;
                }
                writer.write(line + "\r\n");
                writer.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            System.out.println("finish OutputThread ... ");
            /**
             * 如果服务端要结束输出动作，就打印一个"."就行了，
             * 这时循环会退出，线程结束，socket outputstream也会关闭
             */
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}



