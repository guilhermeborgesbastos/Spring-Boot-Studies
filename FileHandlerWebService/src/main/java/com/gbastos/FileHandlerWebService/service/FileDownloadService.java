package com.gbastos.FileHandlerWebService.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {

  @Value("${multipart.location}")
  private String uploadTempDir;

  /**
   * Retrieve mime type from file.
   *
   * @param file
   * @return the MIME type
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws MalformedURLException the malformed URL exception
   */
  private String retrieveMimeTypeFromFile(File file) throws IOException, MalformedURLException {
    URLConnection connection = file.toURL().openConnection();
    String mimeType = connection.getContentType();

    return mimeType;
  }

  /**
   * Download file based on the file's name.
   *
   * @param fileName
   * @return the response entity
   * @throws MalformedURLException the malformed URL exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ResponseEntity<Object> downloadFile(String fileName)
      throws MalformedURLException, IOException {

    final String filePath = uploadTempDir + fileName;
    final File file = new File(filePath);

    final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    final HttpHeaders headers = new HttpHeaders();

    headers.add("Content-Disposition",
        String.format("attachment; filename=\"%s\"", file.getName()));
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");

    final String mimeType = retrieveMimeTypeFromFile(file);

    ResponseEntity<Object> responseEntity =
        ResponseEntity.ok().headers(headers).contentLength(file.length())
            .contentType(MediaType.parseMediaType(mimeType)).body(resource);

    return responseEntity;
  }

  /**
   * Fetches the file names that are placed on the storage.
   *
   * @return the list of files
   */
  public List<String> fetchFileNamesFromStorage() {
    
    final File folder = new File(uploadTempDir);
    final File[] listOfFiles = folder.listFiles();
    
    final List<String> fileNames = new ArrayList<String>();

    for (int i = 0; i < listOfFiles.length; i++) {
      
      if (listOfFiles[i].isFile()) {
        fileNames.add(listOfFiles[i].getName());
      }
    }
    
    return fileNames;
  }
}
