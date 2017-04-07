package com.skypiea.system.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtils;
import com.skypiea.common.utils.IOUtils;
import com.skypiea.common.utils.MD5Utils;
import com.skypiea.common.utils.TimeUtils;
import com.skypiea.system.model.GridFSFileInfo;
import com.skypiea.system.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-21 22:59
 */
@Service
public class FileServiceImpl implements FileService {

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
            ExceptionUtils.getStackTrace(e);
            return SPResult.error("文件上传失败");
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
            ExceptionUtils.getStackTrace(e);
            return SPResult.error("文件上传失败");
        }
    }

    @Override
    public SPResult getFileInfoById(String _id) {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(_id)));
        GridFSFileInfo fileInfo = setFileInfo(file);
        return SPResult.ok(fileInfo);
    }

    @Override
    public SPResult getFileInfoByName(String filename) {
        ArrayList<GridFSFileInfo> fileList = new ArrayList<>();
        List<GridFSDBFile> files = gridFsTemplate.find(Query.query(Criteria.where("filename").is(filename)));
        for (GridFSDBFile file : files) {
            GridFSFileInfo fileInfo = setFileInfo(file);
            fileList.add(fileInfo);
        }
        return SPResult.ok(fileList);
    }

    @Override
    public void getFileById(String _id, HttpServletResponse response) {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(_id)));
        response.setContentType(file.getContentType());
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            file.writeTo(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeIO(os);
        }
    }

    @Override
    public SPResult deleteFileById(String _id) {
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(_id)));
        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
            return SPResult.ok("删除失败");
        }
        return SPResult.ok("删除成功");
    }

    @Override
    public SPResult deleteFileByName(String fileName) {
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("filename").is(fileName)));
        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
            return SPResult.ok("删除失败");
        }
        return SPResult.ok("删除成功");
    }

    @Override
    public void showFileByName(String filename, HttpServletResponse response) {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("filename").is(filename)));
        response.setContentType(file.getContentType());
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            file.writeTo(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeIO(os);
        }
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
        String name = file.getName();
        System.out.println(name);
        String filename = file.getOriginalFilename();       //获取原始文件名
        //获取文件名后缀
        int start = filename.lastIndexOf(".");
        String suffix = StringUtils.substring(filename, start);
        //获取文件名前缀(.前面的部分)
        String prefix = StringUtils.substring(filename, 0, start);
        //对原始文件名进行处理,保证mongodb中的文件名不重复
        filename = MD5Utils.MD5Encode(UUID.randomUUID().toString() + System.currentTimeMillis() + prefix) + suffix;
        System.out.println(filename);
        return gridFsTemplate.store(content, filename, file.getContentType());
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
        fileInfo.setFilename(gridFSFile.getFilename());
        fileInfo.setLength(gridFSFile.getLength());
        fileInfo.setMd5(gridFSFile.getMD5());
        fileInfo.setMetaData(gridFSFile.getMetaData());
        fileInfo.setUploadTime(TimeUtils.date2String(gridFSFile.getUploadDate()));
        return fileInfo;
    }
}
