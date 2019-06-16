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
    modelMap.addAttribute("viewMode", "uploadFile");
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
    modelMap.addAttribute("viewMode", "uploadMultiFile");
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
    modelMap.addAttribute("viewMode", "uploadFileWithAdditionalData");
    
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
    modelMap.addAttribute("viewMode", "uploadFileModelAttribute");
    return "fileUploadView";
  }
}
