package com.skypiea.client.controller;

import com.skypiea.client.service.MongoFileService;
import com.skypiea.common.result.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public SPResult uploadFile(@RequestParam("file") MultipartFile file) {
        SPResult result = fileService.uploadFile(file);
        return result;
    }

    @PostMapping("/upload/multi")
    public SPResult uploadFiles(@RequestParam("file") MultipartFile[] files) {
        SPResult result = fileService.uploadFiles(files);
        return result;
    }

    /**
     * 通过id获取文件信息,返回json
     *
     * @param _id
     * @return
     */
    @GetMapping("/getInfo/id")
    public SPResult getFileInfoById(@RequestParam("_id") String _id) {
        SPResult result = fileService.getFileInfoById(_id);
        return result;
    }

    @GetMapping("/getInfo/name")
    public SPResult getFileInfoByName(@RequestParam("filename") String filename) {
        SPResult result = fileService.getFileInfoByName(filename);
        return result;
    }

    @GetMapping("/id/{_id}")
    public void getFileById(@PathVariable String _id, HttpServletResponse response) {
        fileService.getFileById(_id, response);
    }

    @GetMapping("/name/{filename:.+}")
    public void getFileByName(@PathVariable("filename") String filename, HttpServletResponse response) {
        fileService.getFileByName(filename, response);
    }

    @GetMapping("/delete/id")
    public SPResult deleteFileById(@RequestParam("_id") String _id) {
        SPResult result = fileService.deleteFileById(_id);
        return result;
    }

    @GetMapping("/delete/name")
    public SPResult deleteFileByName(@RequestParam("filename") String filename) {
        SPResult result = fileService.deleteFileByName(filename);
        return result;
    }
}
