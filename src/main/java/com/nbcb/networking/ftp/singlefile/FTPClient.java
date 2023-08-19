package com.nbcb.networking.ftp.singlefile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcb.networking.model.FileInfo;
import com.nbcb.networking.util.ByteUtil;
import com.nbcb.networking.util.FileUtil;

import java.io.*;
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

        /**
         * FileInfo object -> json
         * json��������ļ�����Ϣ��
         * ����filename/filesize/md5֮���
         */
        ObjectMapper objectMapper = new ObjectMapper();
        FileInfo fileInfo = new FileInfo(new File(filePath));
        String json = "";
        try {
            json = objectMapper.writeValueAsString(fileInfo);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /**
         * json���� �������ȴ���
         */
        int length = json.length();




        Socket socket = null;
        OutputStream os = null;

        byte[] buffer = new byte[2048];
        InputStream in = null;
        int n;
        try {
            /**
             * ��������
             */
            socket = new Socket(host, port);
            os = socket.getOutputStream();

            /**
             * (1)����byte of (json) length
             */
            os.write(ByteUtil.int2byte(length));

            /**
             * (2)����byte of json
             */
            os.write(ByteUtil.string2byte(json));

            /**
             * (3)����byte of file
             */
            in = new FileInputStream(filePath);
            while( (n = in.read(buffer , 0, 2048)) != -1){
                os.write(buffer, 0, n);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != os){
                    os.close();
                }
                if(null != socket){
                    socket.close();
                }
                if(null != in){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("finish sending the file ...");


    }
}
