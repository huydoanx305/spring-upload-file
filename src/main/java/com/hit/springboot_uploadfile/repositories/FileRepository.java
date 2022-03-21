package com.hit.springboot_uploadfile.repositories;

import com.hit.springboot_uploadfile.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
