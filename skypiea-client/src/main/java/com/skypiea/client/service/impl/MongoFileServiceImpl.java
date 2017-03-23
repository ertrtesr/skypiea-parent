package com.skypiea.client.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.skypiea.client.pojo.GridFSFileInfo;
import com.skypiea.client.service.MongoFileService;
import com.skypiea.common.http.HttpStatus;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtil;
import com.skypiea.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 22:59
 */
@Service
public class MongoFileServiceImpl implements MongoFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 上传单个文件
     *
     * @param file
     * @return
     */
    @Override
    public SPResult uploadFile(MultipartFile file) {
        try {
            GridFSFile gridFSFile = storeFile(file);
            GridFSFileInfo fileInfo = setFileInfo(gridFSFile);
            return SPResult.ok(fileInfo);
        } catch (IOException e) {
            ExceptionUtil.getStackTrace(e);
            return SPResult.build(HttpStatus.OK, "文件上传失败");
        }
    }

    @Override
    public SPResult uploadFiles(MultipartFile[] files) {
        ArrayList<GridFSFileInfo> fileList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                GridFSFile gridFSFile = storeFile(file);
                GridFSFileInfo fileInfo = setFileInfo(gridFSFile);
                fileList.add(fileInfo);
            }
            return SPResult.ok(fileList);
        } catch (IOException e) {
            ExceptionUtil.getStackTrace(e);
            return SPResult.build(HttpStatus.OK, "部分文件上传失败", fileList);
        }
    }

    @Override
    public SPResult findFileById(String _id) {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(_id)));
        GridFSFileInfo fileInfo = setFileInfo(file);
        return SPResult.ok(fileInfo);
    }

    @Override
    public SPResult findFileByName(String fileName) {
        ArrayList<GridFSFileInfo> fileList = new ArrayList<>();
        List<GridFSDBFile> files = gridFsTemplate.find(Query.query(Criteria.where("fileName").is(fileName)));
        for (GridFSDBFile file : files) {
            GridFSFileInfo fileInfo = setFileInfo(file);
            fileList.add(fileInfo);
        }
        return SPResult.ok(fileList);
    }

    @Override
    public SPResult deleteFileById(String _id) {
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(_id)));
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
            return SPResult.ok("删除失败");
        }
        return SPResult.ok("删除成功");
    }

    @Override
    public SPResult deleteFileByName(String fileName) {
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("filename").is(fileName)));
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
            return SPResult.ok("删除失败");
        }
        return SPResult.ok("删除成功");
    }

    /**
     * 存储文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    private GridFSFile storeFile(MultipartFile file) throws IOException {
        InputStream content = file.getInputStream();
        String fileName = file.getOriginalFilename();       //获取原始文件名
        int startPos = StringUtils.indexOf(fileName, ".");
        String contentType = StringUtils.substring(fileName, startPos + 1);
        return gridFsTemplate.store(content, fileName, contentType);
    }

    /**
     * 设置GirdFsFile文件信息
     *
     * @param gridFSFile
     * @return
     */
    private GridFSFileInfo setFileInfo(GridFSFile gridFSFile) {
        GridFSFileInfo fileInfo = new GridFSFileInfo();
        fileInfo.set_id(gridFSFile.getId().toString());
        fileInfo.setContentType(gridFSFile.getContentType());
        fileInfo.setFileName(gridFSFile.getFilename());
        fileInfo.setLength(gridFSFile.getLength());
        fileInfo.setMd5(gridFSFile.getMD5());
        fileInfo.setMetaData(gridFSFile.getMetaData());
        fileInfo.setUploadTime(TimeUtils.date2String(gridFSFile.getUploadDate()));
        return fileInfo;
    }
}
