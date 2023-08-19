package com.nbcb.networking.model;

import cn.hutool.crypto.SecureUtil;

import java.io.File;

/**
 * 文件元数据
 */
public class FileInfo {

    public FileInfo(File file) {
        this.fileName = file.getName();
        this.filesize = file.length();
        this.md5 = SecureUtil.md5(file);
    }

    public FileInfo() {
    }

    private String fileName;
    private long filesize;
    private String md5;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", filesize=" + filesize +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
