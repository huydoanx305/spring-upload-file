package com.hit.springboot_uploadfile.services;

import com.hit.springboot_uploadfile.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<File> createFile(MultipartFile[] files);
    void deleteFile(Long id);
}
