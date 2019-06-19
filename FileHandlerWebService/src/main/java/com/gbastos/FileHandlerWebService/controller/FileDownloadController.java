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
