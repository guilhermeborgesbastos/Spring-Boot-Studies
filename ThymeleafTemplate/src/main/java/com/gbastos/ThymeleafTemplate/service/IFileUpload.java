package com.gbastos.ThymeleafTemplate.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUpload {
  
  Boolean uploadFile(MultipartFile files) throws IOException;
  Boolean uploadMultiFile(MultipartFile[] files) throws IOException;
}
