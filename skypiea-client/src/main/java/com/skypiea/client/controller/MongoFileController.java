package com.skypiea.client.controller;

import com.skypiea.client.service.MongoFileService;
import com.skypiea.common.result.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 22:07
 */
@RestController
@RequestMapping("/client/file")
public class MongoFileController {

    @Autowired
    private MongoFileService fileService;

    @PostMapping("/upload/single")
    public SPResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        SPResult result = fileService.uploadFile(file);
        return result;
    }

    @PostMapping("/upload/multi")
    public SPResult uploadFiles(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        SPResult result = fileService.uploadFiles(files);
        return result;
    }

    @GetMapping("/find/id")
    public SPResult findFileById(@RequestParam("_id") String _id) {
        SPResult result = fileService.findFileById(_id);
        return result;
    }

    @GetMapping("/find/name")
    public SPResult findFileByName(@RequestParam("fileName") String fileName) {
        SPResult result = fileService.findFileByName(fileName);
        return result;
    }

    @GetMapping("/delete/id")
    public SPResult deleteFileById(@RequestParam("_id") String _id) {
        SPResult result = fileService.deleteFileById(_id);
        return result;
    }

    @GetMapping("/delete/name")
    public SPResult deleteFileByName(@RequestParam("fileName") String fileName) {
        SPResult result = fileService.deleteFileByName(fileName);
        return result;
    }
}
