package com.nbcb.networking.util;

import java.io.*;

public class FileUtil {

    /**
     * 根据文件路径，返回该文件的二进制流
     * @param filePath
     * @return
     */
    public static byte[] toByte(String filePath){
        byte[] content = null;

        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(filePath);
            out = new ByteArrayOutputStream();

            int b = 0;
            while((b = in.read()) != -1){
                out.write(b);
            }
            content = out.toByteArray();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return content;
    }

}
