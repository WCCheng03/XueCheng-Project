package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MinioTest
 * @Author Chen9
 * @Date 2023/11/16 15:53
 * @VERSION 1.0
 * @Description 测试minio的sdk
 * @Program XueCheng-Project
 **/
public class MinioTest {
    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://localhost:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();
    //通过拓展名获得媒体类型
    //根据扩展名取出mimeType
    ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".mp4");
    String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;//通用mimeType，字节流


    @Test
    public void testUpload() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        UploadObjectArgs testbuckte = UploadObjectArgs.builder()
                .bucket("testbucket")//桶
                .filename("C:\\Users\\24348\\Desktop\\Basement\\Reze.jpg")//指定本地文件路径
                .object("MyReze.jpg") // 对象名，在桶下存储文件
//                .object("test/MyReze.jpg") // 存放到子目录下
//                .contentType("")//设置媒体文件类型
                .build();

        minioClient.uploadObject(testbuckte);
    }

    @Test
    public void testDelete() {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket("testbucket").object("MyReze.jpg").build());
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    //查询文件
    @Test
    public void getFile() {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket("testbucket").object("MyReze.jpg").build();
        try (
                FilterInputStream inputStream = minioClient.getObject(getObjectArgs);
                FileOutputStream outputStream = new FileOutputStream(new File("D:\\picture\\Reze.jpg"));
        ) {
            IOUtils.copy(inputStream, outputStream);
            //校验文件的完整性对文件的内容进行md5
            FileInputStream fileInputStream1 = new FileInputStream(new File("F:\\directory\\picture\\Reze.jpg"));
            String source_md5 = DigestUtils.md5Hex(fileInputStream1);
            FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\24348\\Desktop\\Basement\\Reze.jpg"));
            String local_md5 = DigestUtils.md5Hex(fileInputStream);
            if (source_md5.equals(local_md5)) {
                System.out.println("下载成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
