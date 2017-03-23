package com.skypiea.client.service;

import com.skypiea.common.result.SPResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 22:08
 */
public interface MongoFileService {

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

    SPResult findFileById(String _id);

    SPResult findFileByName(String fileName);

    SPResult deleteFileById(String _id);

    SPResult deleteFileByName(String fileName);
}
