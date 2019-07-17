package com.gbastos.ThymeleafTemplate.model;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class FormDataWithFile {

    private String name;
    private String email;
    private MultipartFile file;
}
