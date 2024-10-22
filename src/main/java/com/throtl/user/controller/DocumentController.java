package com.throtl.user.controller;


import com.throtl.user.service.AzureBlobService;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/th/banner")
public class DocumentController {


    @Autowired
    AzureBlobService azureBlobService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CommonUtil commonUtil;
    // Endpoint to upload the document
    @PostMapping("/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream fileStream = file.getInputStream();
        String fileName = file.getOriginalFilename();

        return azureBlobService.uploadFile(fileStream, fileName);
    }

    // Endpoint to generate download link for the document
    @GetMapping("/download-link")
    public ResponseEntity<Object> getDownloadLink() {

        try{
            return customerService.getTagEzeeDoc(true);
        }catch (Exception e){

        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

//        return azureBlobService.generateDownloadLink(fileName);
    }

}
