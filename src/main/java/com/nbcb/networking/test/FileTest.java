package com.nbcb.networking.test;

import com.nbcb.networking.model.FileInfo;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("/Users/zhoushuo/Documents/tmp/aa.txt");
        System.out.println(file.length());

        FileInfo fileInfo = new FileInfo(file);
        System.out.println(fileInfo);

    }
}
