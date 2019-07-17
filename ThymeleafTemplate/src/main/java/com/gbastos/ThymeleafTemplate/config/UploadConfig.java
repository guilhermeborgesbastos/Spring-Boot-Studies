package com.gbastos.ThymeleafTemplate.config;

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

/**
 * The Class UploadConfiguration is in charge of configuring the Multipart properties such as 'Max
 * file size', 'Multipart Location', etc...
 */
@Configuration
public class UploadConfig {

    private static final Logger LOG = LoggerFactory.getLogger(UploadConfig.class);

    @Value("${multipart.file-size-threshold:-1}")
    private int maxInMemorySize;
    
    @Value("${multipart.max-file-size:10240}")
    private String uploadMaxFileSize;
    
    @Value("${multipart.location}")
    private String uploadTempDir;

    private Long parseSize(String size) {
        size = size.toUpperCase();
        if (size.endsWith("KB")) {
            return Long.valueOf(size.substring(0, size.length() - 2)) * 1024;
        }
        if (size.endsWith("MB")) {
            return Long.valueOf(size.substring(0, size.length() - 2)) * 1024 * 1024;
        }
        return Long.valueOf(size);
    }

    @Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
    public CommonsMultipartResolver multipartResolver() {
      
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        
        if (StringUtils.isNotBlank(uploadTempDir)) {
            try {
                commonsMultipartResolver.setUploadTempDir(new FileSystemResource(uploadTempDir));
            }
            catch (IOException e) {
                LOG.warn(String.format("Illegal or not existing folder %s (temporary upload directory)!", uploadTempDir), e);
            }
        }
        
        commonsMultipartResolver.setMaxUploadSize(parseSize(uploadMaxFileSize));
        commonsMultipartResolver.setMaxInMemorySize(maxInMemorySize);
        
        return commonsMultipartResolver;
    }
}