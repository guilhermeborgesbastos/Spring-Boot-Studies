package com.gbastos.ThymeleafTemplate.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService implements IFileUpload{

  @Value("${multipart.location}")
  private String uploadTempDir;

  /**
   * Uploads the file using the multipart location property located in the application.properties
   * file.
   * 
   * @param file
   * @throws IOException
   * @throws FileNotFoundException
   */
  private void writeFile(MultipartFile file) throws IOException, FileNotFoundException {
    File convertFile = new File(uploadTempDir + file.getOriginalFilename());
    convertFile.createNewFile();
    FileOutputStream fileOut = new FileOutputStream(convertFile);
    fileOut.write(file.getBytes());
    fileOut.close();
  }

  /**
   * Uploads a single file.
   *
   * @param file
   * @return the boolean true, if succeed.
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public Boolean uploadFile(MultipartFile file) throws IOException {
    writeFile(file);
    return true;
  }

  /**
   * Uploads multiple files.
   *
   * @param files
   * @return the boolean true, if succeed.
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public Boolean uploadMultiFile(MultipartFile[] files) throws IOException {
    for (MultipartFile file : files) {
      writeFile(file);
    }
    return true;
  }
}
