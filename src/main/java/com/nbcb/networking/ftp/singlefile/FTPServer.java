package com.nbcb.networking.ftp.singlefile;

import com.nbcb.networking.util.ByteUtil;
import com.nbcb.networking.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {

    public static void main(String[] args) {
        System.out.println("start ftp server ...");


        /**
         * 服务端监听的端口
         */
        int port = 9999;
        ServerSocket serverSocket = null;
        Socket connection = null;
        InputStream is = null;

        /**
         * 服务端把这个文件保存到如下目录下
         */
        String targetFilePath = "/Users/zhoushuo/Documents/tmp/target/aa.txt";

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server start listening on port : " + port);

            while(true){
                connection = serverSocket.accept();
                System.out.println("server get connection from client ...");

                is = connection.getInputStream();

                /**
                 * (1)读取byte of (json) length
                 */
                byte[] byteInt = new byte[4];
                is.read(byteInt);
                int length = ByteUtil.byte2int(byteInt);
                System.out.println("length of json: " + length);

                /**
                 * (2)读取byte of json
                 */
                byte[] byteJson = new byte[length];
                is.read(byteJson);
                String json = ByteUtil.byte2String(byteJson);
                System.out.println("json from client: " + json);

                /**
                 * (3)读取文件
                 */
                FileUtil.streamToFile(is, targetFilePath);
                System.out.println("ftp server finish save file to local ...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
