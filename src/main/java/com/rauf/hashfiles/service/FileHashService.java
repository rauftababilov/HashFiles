package com.rauf.hashfiles.service;

import com.rauf.hashfiles.entity.FileHashDTO;
import com.rauf.hashfiles.entity.FileHashEntity;
import com.rauf.hashfiles.entity.FileHashRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileHashService {
    @Autowired
    FileHashRepository fileHashRepository;

    public FileHashDTO addFileHash(byte[] file, String filename, String userId) {
        String md5 = DigestUtils.md5Hex(file);
        String sha256 = DigestUtils.sha256Hex(file);
        FileHashEntity fhe = new FileHashEntity();
        fhe.setMd5(md5);
        fhe.setSha256(sha256);
        fhe.setUserId(userId);
        fhe.setFilename(filename);
        FileHashDTO fh = FileHashDTO.from(fileHashRepository.save(fhe));
        return fh;
    }

    public boolean deleteByHash(String hash, String userId) {
        return fileHashRepository.deleteByHashAndUserId(hash, userId) > 0;
    }

    public List<FileHashDTO> findFileHashes(String hash) {
        return fileHashRepository.findByHash(hash).stream().map(FileHashDTO::from)
                .collect(Collectors.toList());
    }

}
