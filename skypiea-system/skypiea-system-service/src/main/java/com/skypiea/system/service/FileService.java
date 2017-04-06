package com.skypiea.system.service;

import com.skypiea.common.result.SPResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 22:08
 */
public interface FileService {

    /**
     * 上传单个文件
     *
     * @param file
     * @return
     */
    SPResult uploadFile(MultipartFile file);

    /**
     * 上传多个文件
     *
     * @param files
     * @return
     */
    SPResult uploadFiles(MultipartFile[] files);

    SPResult getFileInfoById(String _id);

    SPResult getFileInfoByName(String fileName);

    void getFileById(String _id, HttpServletResponse response);

    SPResult deleteFileById(String _id);

    SPResult deleteFileByName(String fileName);

    void showFileByName(String filename, HttpServletResponse response);
}
