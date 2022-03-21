package com.hit.springboot_uploadfile.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hit.springboot_uploadfile.entity.File;
import com.hit.springboot_uploadfile.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<File> createFile(MultipartFile[] files) {
        List<File> fileList = new ArrayList<>();
        try {
            for(MultipartFile file : files) {
                File fileDB = new File();
                Map<?, ?> map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                fileDB.setImageUrl(map.get("secure_url").toString());
                fileDB.setImagePublicId(map.get("public_id").toString());
                fileList.add(fileDB);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileRepository.saveAll(fileList);
    }

    @Override
    public void deleteFile(Long id) {
        Optional<File> file = fileRepository.findById(id);
        try {
            if(file.isPresent()) {
                cloudinary.uploader().destroy(file.get().getImagePublicId(), ObjectUtils.emptyMap());
                fileRepository.deleteById(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
