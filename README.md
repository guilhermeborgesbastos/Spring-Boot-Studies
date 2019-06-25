<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)

[![Spring Boot Studies - File Handling By Guilherme Borges Bastos](https://img.youtube.com/vi/4_mVGYfXX38/maxresdefault.jpg)](http://www.youtube.com/watch?v=4_mVGYfXX38 "Spring Boot Studies - File Handling")

# Spring Boot - File Handling

In this chapter, we will learn how to `upload` and `download` files by using a _Web Service_.  A UI Interface was also created using the MVC application design model.

### Upload
![File Handling - Upload Simple User Interface](img/Upload-Single-File-Upload.png)

### Download
![File Handling - Download Simple User Interface](img/Upload-Single-File-Download.png)


## File Uploading

For uploading a file, we can use `MultipartFile` as a `Request Parameter` and this API should consume _Multi-Part form_ data value. 

Let's start by creating the `Service` layer −

### Service Layer

```java
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
    final File convertFile = new File(uploadTempDir + file.getOriginalFilename());
    convertFile.createNewFile();
    final FileOutputStream fileOut = new FileOutputStream(convertFile);
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
```

After creating the service layer it's time to work on the `@Controller` -

### Controller Layer

> ### All the static resources used on the User Interface are available at  [this link](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/FileHandling/FileHandlerWebService/src/main/resources).

Below is the controller's code -

```java
package com.gbastos.FileHandlerWebService.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gbastos.FileHandlerWebService.model.FormDataWithFile;
import com.gbastos.FileHandlerWebService.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileUploadController it is a simple sample of a Web Service used to handle Filed on
 * Spring Boot.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

  private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
  private static final String REQUEST_URI = "requestURI";

  @Autowired
  private FileUploadService fileUploadService;

  @GetMapping(value = "/")
  public String displayForm() {
    return "fileUploadForm";
  }

  @PostMapping(value = "/uploadFile")
  public String submit(@RequestParam("file") final MultipartFile file, final ModelMap modelMap) {

    try {
      fileUploadService.uploadFile(file);
    } catch (IOException e) {
      LOG.error(e.getMessage(), e.getCause());
    }

    modelMap.addAttribute("file", file);
    modelMap.addAttribute(REQUEST_URI, "uploadFile");
    return "fileUploadView";
  }

  @PostMapping(value = "/uploadMultiFile")
  public String submit(@RequestParam("files") final MultipartFile[] files,
      final ModelMap modelMap) {

    try {
      fileUploadService.uploadMultiFile(files);
    } catch (IOException e) {
      LOG.error(e.getMessage(), e.getCause());
    }

    modelMap.addAttribute("files", files);
    modelMap.addAttribute(REQUEST_URI, "uploadMultiFile");
    return "fileUploadView";
  }

  @PostMapping(value = "/uploadFileWithAdditionalData")
  public String submit(@RequestParam final MultipartFile file, @RequestParam final String name,
      @RequestParam final String email, final ModelMap modelMap) {

    try {
      fileUploadService.uploadFile(file);
    } catch (IOException e) {
      LOG.error(e.getMessage(), e.getCause());
    }

    modelMap.addAttribute("name", name);
    modelMap.addAttribute("email", email);
    modelMap.addAttribute("file", file);
    modelMap.addAttribute(REQUEST_URI, "uploadFileWithAdditionalData");
    
    return "fileUploadView";
  }

  @PostMapping(value = "/uploadFileModelAttribute")
  public String submit(@ModelAttribute final FormDataWithFile formDataWithFile,
      final ModelMap modelMap) {

    try {
      fileUploadService.uploadFile(formDataWithFile.getFile());
    } catch (IOException e) {
      LOG.error(e.getMessage(), e.getCause());
    }

    modelMap.addAttribute("formDataWithFile", formDataWithFile);
    modelMap.addAttribute(REQUEST_URI, "uploadFileModelAttribute");
    return "fileUploadView";
  }
}
```

## File Downloading

For file download, you should use `InputStreamResource` for downloading a File. We need to set the `HttpHeader Content-Disposition` in the Response and need to specify the response **Media Type** of the application. To do that, since there are several different Media Types we need to create a utilitary method to do that -

```java
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
```

Once the utilitary method is done, it's time to create the method into the Service layer that will output the downloaded file -

```java
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
```

**Note** − *In the following example, file should be available on the specified path where the application is running.*

In our front-end we have a **Dropdown** that is loaded with the names of all the files that were uploaded through your application, in order to do that, we need to create the service for it, this service will be injected and used in the `controller` further on at this example -

```java
  /**
   * Fetches the file names that are placed on the storage.
   *
   * @return the list of file's names
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
```

> ### The complete class can be found at [this link](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/blob/FileHandling/FileHandlerWebService/src/main/java/com/gbastos/FileHandlerWebService/service/FileDownloadService.java).

After creating the service layer it's time to work on the `@Controller` -

### Controller Layer

> ### All the static resources used on the User Interface are available at  [this link](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/FileHandling/FileHandlerWebService/src/main/resources).

Below is the controller's code -

```java
package com.gbastos.FileHandlerWebService.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gbastos.FileHandlerWebService.service.FileDownloadService;

/**
 * The Class FileDownloadController it is a simple sample of a Web Service used to handle Files on
 * Spring Boot.
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {

  private static final Logger LOG = LoggerFactory.getLogger(FileDownloadController.class);

  @Autowired
  private FileDownloadService fileDownloadService;
  
  @GetMapping(value = "/")
  public String displayForm(final ModelMap modelMap) {
    final List<String> fileNames = fileDownloadService.fetchFileNamesFromStorage();
    modelMap.addAttribute("files", fileNames);
    
    return "fileDownloadForm";
  }
  
  @RequestMapping(value = "/file", method = RequestMethod.POST) 
  public ResponseEntity<Object> downloadFile(@RequestParam("fileName") final String fileName) {
    
    ResponseEntity<Object> responseEntity = null;
    
    try {
      responseEntity = fileDownloadService.downloadFile(fileName);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e.getCause());
    }

    return responseEntity;
  }
}
```
