package com.banker.experience.controller;

import com.banker.experience.service.AmazonHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
@CrossOrigin(origins = "http://localhost:19006")
public class BucketController {

    private AmazonHelperService amazonHelperService;

    @Autowired
    BucketController(AmazonHelperService amazonHelperService) {
        this.amazonHelperService = amazonHelperService;
    }

    @PostMapping("uploadFile")
    public String uploadFile(@RequestPart(value = "photo") MultipartFile file) {
        return this.amazonHelperService.uploadFile(file);
    }

    // Delete does not work yet
    @DeleteMapping("deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonHelperService.deleteFileFromS3Bucket(fileUrl);
    }
}