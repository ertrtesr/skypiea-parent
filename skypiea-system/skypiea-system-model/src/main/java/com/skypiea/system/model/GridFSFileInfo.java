package com.skypiea.system.model;

import com.mongodb.DBObject;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-20 10:22
 */

public class GridFSFileInfo {

    private String _id;
    private String contentType;
    private String filename;
    private long length;
    private String md5;
    private DBObject metaData;
    private String uploadTime;

    public GridFSFileInfo() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public DBObject getMetaData() {
        return metaData;
    }

    public void setMetaData(DBObject metaData) {
        this.metaData = metaData;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
