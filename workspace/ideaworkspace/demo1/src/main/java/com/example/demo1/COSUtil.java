package com.example.demo1;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class COSUtil {

    private static final String ACCESSKEY = "AKIDTgbBUW6p42jSt2ozqcFh4JOgqGVUlhvK";//开发者拥有的项目身份识别 ID，用以身份认证
    private static final String SECRETKEY = "Z3LNA4i6ECtqWw5zby7KGuHMAJBm56qJ";//开发者拥有的项目身份密钥
    private static final String BUCKETNAME = "test01-1258109648";//COS 中用于存储数据的容器
    //private static final String APPID = "1300277137";//开发者访问 COS 服务时拥有的用户维度唯一资源标识，用以标识资源
    private static final String REGIONID = "ap-beijing";//域名中的地域信息
//    private static final String KEY="yunnan-project/";
//    private static final String KEY01="MyFile1/1.jpg";


    /**
     * 初始化CosClient相关配置， appid、accessKey、secretKey、region
     * @return
     */
    public static COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey),登陆
        COSCredentials cred = new BasicCOSCredentials(ACCESSKEY, SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
       // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        //String bucketName = BUCKETNAME;
        return cosClient;
    }

    /**
     * 上传文件
     * @return
     * //绝对路径和相对路径都OK
     */
    public static String uploadFile(String key,File localFile) {
//        File localFile = new File("E:\\software\\JavaProject\\demo\\demo20\\src\\main\\resources\\1.jpg");
//        File localFile = new File("D:\\test.jpg");

         key = key.replace("\\","/");
        if (key.contains(":")){
            key = key.substring(3);
       }
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, key, localFile);

        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
        putObjectRequest.setStorageClass(StorageClass.Standard);

        COSClient cc = getCosClient();
        try {
            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            System.out.println(etag);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cc.shutdown();
        return null;
    }

    // 腾讯云COS高级（大文件）上传,不用考虑分块
    public static void fileCOS( String key,File file) throws Exception {

        COSClient cosClient = getCosClient();
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        //分装与上传
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, key, file);
        Upload upload = transferManager.upload(putObjectRequest);
        // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
        upload.waitForUploadResult();
        // 关闭 TransferManger
        transferManager.shutdownNow();


    }

    //上传文件夹
    public static void getFiles(String path) {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    getFiles(files[i].getPath());
                } else {
                    uploadFile(files[i].getPath(),files[i]);
                }

            }
        } else {
            uploadFile(file.getPath(),file);
        }
    }


    /**
     * 下载文件
     * @param downFile
     * @param key
     * @return
     */
    public static void downLoadFile(String key,File downFile) {
//        File downFile = new File("D:\\1.jpg");
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        try {
            COSClient cc = getCosClient();
            GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKETNAME, key);

//        ObjectMetadata downObjectMeta = cc.getObject(getObjectRequest, downFile);
            // 下载文件
            // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
            TransferManager transferManager = new TransferManager(cc, threadPool);

            Download download = transferManager.download(getObjectRequest, downFile);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            download.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow();
        }

//        cc.shutdown();
//        String etag = downObjectMeta.getETag();
    }

    /**
     * 删除文件
     * @param bucketName
     * @param key
     */
    public static void deleteFile(String bucketName, String key) {
        COSClient cc = getCosClient();
        try {
            cc.deleteObject(bucketName, key);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cc.shutdown();
        }

    }

    /**
     * 创建桶
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static Bucket createBucket(String bucketName) throws CosClientException, CosServiceException {
        COSClient cc = getCosClient();
        Bucket bucket = null;
        try {
            bucket = cc.createBucket(bucketName);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
        }
        return bucket;
    };

    /**
     * 删除桶
     * @param bucketName
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteBucket(String bucketName) throws CosClientException, CosServiceException {
        COSClient cc = getCosClient();
        try {
            cc.deleteBucket(bucketName);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
        }
    };

    /**
     * 判断桶是否存在
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static boolean doesBucketExist(String bucketName) throws CosClientException, CosServiceException {
        COSClient cc = getCosClient();
        boolean bucketExistFlag = cc.doesBucketExist(bucketName);
        return bucketExistFlag;
    };

    /**
     * 查看桶文件
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static ObjectListing listObjects(String bucketName) throws CosClientException, CosServiceException {
        COSClient cc = getCosClient();

        // 获取 bucket 下成员（设置 delimiter）
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        // 设置 list 的 prefix, 表示 list 出来的文件 key 都是以这个 prefix 开始
        listObjectsRequest.setPrefix("");
        // 设置 delimiter 为/, 即获取的是直接成员，不包含目录下的递归子成员
        listObjectsRequest.setDelimiter("/");
        // 设置 marker, (marker 由上一次 list 获取到, 或者第一次 list marker 为空)
        listObjectsRequest.setMarker("");
        // 设置最多 list 100 个成员,（如果不设置, 默认为 1000 个，最大允许一次 list 1000 个 key）
        listObjectsRequest.setMaxKeys(100);

        ObjectListing objectListing = cc.listObjects(listObjectsRequest);
        // 获取下次 list 的 marker
        String nextMarker = objectListing.getNextMarker();
        // 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        boolean isTruncated = objectListing.isTruncated();
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        for (COSObjectSummary cosObjectSummary : objectSummaries) {
            // get file path
            String key = cosObjectSummary.getKey();
            // get file length
            long fileSize = cosObjectSummary.getSize();
            // get file etag
            String eTag = cosObjectSummary.getETag();
            // get last modify time
            Date lastModified = cosObjectSummary.getLastModified();
            // get file save type
            String StorageClassStr = cosObjectSummary.getStorageClass();
        }
        return objectListing;
    }

    /**
     *查询一个 Bucket 所在的 Region。
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static String getBucketLocation(String bucketName) throws CosClientException , CosServiceException{
        COSClient cosClient = getCosClient();
        String location = cosClient.getBucketLocation(bucketName);
        // bucket 的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        return location;
    }
}


