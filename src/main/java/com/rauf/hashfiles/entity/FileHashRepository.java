package com.rauf.hashfiles.entity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FileHashRepository extends CrudRepository<FileHashEntity, FileHashEntity> {

    @Query("delete from FileHashEntity f where f.userId = ?2 and (f.md5 = ?1 or f.sha256=?1)")
    @Modifying
    @Transactional
    int deleteByHashAndUserId(String hash, String userId);

    @Query("select f from FileHashEntity f where f.md5 = ?1 or f.sha256=?1")
    List<FileHashEntity> findByHash(String hash);

}
