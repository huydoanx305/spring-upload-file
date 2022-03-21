package com.hit.springboot_uploadfile.controllers;

import com.hit.springboot_uploadfile.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files) {
        return ResponseEntity.ok().body(fileService.createFile(files));
    }

    @DeleteMapping("/delete-file/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }

}
