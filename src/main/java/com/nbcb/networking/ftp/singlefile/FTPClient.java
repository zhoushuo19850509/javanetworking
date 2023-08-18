package com.nbcb.networking.ftp.singlefile;

import com.nbcb.networking.util.FileUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FTPClient {
    public static void main(String[] args) {
        System.out.println("start ftp client ...");

        /**
         * ftp server ip/port
         */
        String host = "localhost";
        int port = 9999;

        /**
         * file to be transfered ...
         */
        String filePath = "/Users/zhoushuo/Documents/tmp/aa.txt";
        byte[] content = FileUtil.toByte(filePath);

        Socket socket = null;
        OutputStream os = null;

        try {
            socket = new Socket(host, port);
            os = socket.getOutputStream();
            os.write(content);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("finish sending the file ...");


    }
}
