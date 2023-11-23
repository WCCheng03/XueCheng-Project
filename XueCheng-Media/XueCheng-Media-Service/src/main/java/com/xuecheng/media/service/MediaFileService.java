package com.xuecheng.media.service;


import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;

import java.io.File;


/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2022/9/10 8:55
 */
public interface MediaFileService {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author Mr.M
     * @date 2022/9/10 8:57
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

    /**
     * 上传文件
     *
     * @param companyId           机构id
     * @param uploadFileParamsDto 上传文件信息
     * @param localFilePath       文件磁盘路径
     * @Param objectName 如果传入，就按传入的目录存储
     * @return 文件信息
     */
    UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath,String objectName);

    /**
     * 将文件上传到minio
     *
     * @param localFilePath 文件本地路径
     * @param mimeType      媒体类型
     * @param bucket        桶
     * @param objectName    对象名
     * @return
     */
    boolean addMediaFilesToMinIO(String localFilePath, String mimeType, String bucket, String objectName);

    /**
     * @param
     * @author CHEN9
     * @date 2023/11/16 20:02
     * @description 添加媒体文件到数据库
     **/
    MediaFiles addMediaFilesToDb(Long companyId, String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName);

    /**
     * 从minio下载文件
     *
     * @param bucket     桶
     * @param objectName 对象名称
     * @return 下载后的文件
     */
    File downloadFileFromMinIO(String bucket, String objectName);

    /**
     * @param mediaId 媒资id
     * @return MediaFiles 文件信息
     * @author CHEN9
     * @date 2023/11/21 14:13
     * @description 根据媒资id查询文件信息
     **/
    MediaFiles getFileById(String mediaId);
}
