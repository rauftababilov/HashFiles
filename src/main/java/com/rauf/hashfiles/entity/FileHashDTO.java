package com.rauf.hashfiles.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FileHashDTO implements IFileHash {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("sha256")
    private String sha256;

    @JsonProperty("md5")
    private String md5;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public static FileHashDTO from(IFileHash fileHash) {
        if (fileHash == null) {
            return null;
        }
        FileHashDTO fh = new FileHashDTO();
        fh.setFilename(fileHash.getFilename());
        fh.setMd5(fileHash.getMd5());
        fh.setSha256(fileHash.getSha256());
        fh.setUserId(fileHash.getUserId());
        return fh;
    }
}
