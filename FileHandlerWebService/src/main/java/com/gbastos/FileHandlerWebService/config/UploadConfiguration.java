package com.gbastos.FileHandlerWebService.config;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class UploadConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadConfiguration.class);

    @Value("${multipart.file-size-threshold:-1}")
    private int maxInMemorySize;
    
    @Value("${multipart.max-file-size:10240}")
    private String uploadMaxFileSize;
    
    @Value("${multipart.location}")
    private String uploadTempDir;

    @Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
    public CommonsMultipartResolver multipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        if (StringUtils.isNotBlank(uploadTempDir)) {
            try {
                commonsMultipartResolver.setUploadTempDir(new FileSystemResource(uploadTempDir));
            }
            catch (IOException e) {
                LOGGER.warn(String.format("Illegal or not existing folder %s (temporary upload directory)!", uploadTempDir), e);
            }
        }
        commonsMultipartResolver.setMaxUploadSize(parseSize(uploadMaxFileSize));
        commonsMultipartResolver.setMaxInMemorySize(maxInMemorySize);
        return commonsMultipartResolver;
    }
  
    long parseSize(String size) {
        size = size.toUpperCase();
        if (size.endsWith("KB")) {
            return Long.valueOf(size.substring(0, size.length() - 2)) * 1024;
        }
        if (size.endsWith("MB")) {
            return Long.valueOf(size.substring(0, size.length() - 2)) * 1024 * 1024;
        }
        return Long.valueOf(size);
    }
}