package com.rauf.hashfiles.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "hash_files")
@Entity
@IdClass(FileHashEntity.class)
public class FileHashEntity implements IFileHash, Serializable {

    @Id
    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Id
    @Column(name = "file_name", nullable = false)
    private String filename;

    @Id
    @Column(name = "sha256", nullable = false, length = 64)
    private String sha256;

    @Id
    @Column(name = "md5", nullable = false, length = 32)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileHashEntity that = (FileHashEntity) o;
        return userId.equals(that.userId) &&
                filename.equals(that.filename) &&
                sha256.equals(that.sha256) &&
                md5.equals(that.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, filename, sha256, md5);
    }
}
