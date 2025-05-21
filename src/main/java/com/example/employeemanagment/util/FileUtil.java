package com.example.employeemanagment.util;

import com.example.employeemanagment.entity.Employee;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

@Component
public class FileUtil {

    @Value("${picture.upload.path}")
    private String uploadDirectory;

    @SneakyThrows
    public void uploadImage(Employee employee, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadDirectory, fileName));
            employee.setPicture(fileName);
        }
    }

    @SneakyThrows
    public byte[] getPicture(String picName) {
        File file = new File(uploadDirectory, picName);
        if (file.exists()) {
            byte[] byteArray = IOUtils.toByteArray(new FileInputStream(file));
            if (byteArray.length == 0) {
                return null;
            }
            return byteArray;
        }
        return null;
    }
}
