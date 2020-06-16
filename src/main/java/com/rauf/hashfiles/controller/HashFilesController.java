package com.rauf.hashfiles.controller;

import com.rauf.hashfiles.entity.FileHashDTO;
import com.rauf.hashfiles.entity.IFileHash;
import com.rauf.hashfiles.service.FileHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.rauf.hashfiles.controller.UserInjector.USER_ID;

@Controller
@RequestMapping(value = "/file_hashes", consumes = "*/*", produces = "application/json")
public class HashFilesController {

    @Autowired
    FileHashService fileHashService;

    @RequestMapping(value = "/{hash}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFileHashesHash(@ModelAttribute(USER_ID) String userId, @PathVariable("hash") String hash) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean removed = fileHashService.deleteByHash(hash, userId);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{hash}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<? extends IFileHash>> getFileHashes(@PathVariable("hash") String hash) {
        List<FileHashDTO> fileHashes = fileHashService.findFileHashes(hash);
        return fileHashes.isEmpty() ? ResponseEntity.notFound().build()
                : new ResponseEntity<>(fileHashes, HttpStatus.OK);
    }

    @RequestMapping(value = "",
            produces = {"application/json"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    public ResponseEntity<? extends IFileHash> postFileHashes(@ModelAttribute(USER_ID) String userId,
                                                              @RequestPart("file") MultipartFile file) throws IOException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        FileHashDTO fh = fileHashService.addFileHash(file.getBytes(), file.getOriginalFilename(), userId);
        return new ResponseEntity<>(fh, HttpStatus.CREATED);
    }

}

