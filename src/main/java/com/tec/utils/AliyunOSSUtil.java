package com.tec.utils;

//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.OSSException;
//import com.aliyun.oss.model.*;

//@Slf4j
public class AliyunOSSUtil {


    //以下5个参数替换成自己的
    private static final String bucketName = "hlwyy-tasly";
    private static final String endpoint = "http://hlwyy-tasly.oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "LTAIDtCLZWLmM5n5";
    private static final String accessKeySecret = "qeTYldEQDPsr2GPHmgNwIy7foKqRr6";
    private static final String fileHost = "";
    private static String FILE_URL;

    /**
     * 上传文件。
     *
     * @param file 需要上传的文件路径
     * @return 如果上传的文件是图片的话，会返回图片的"URL"，如果非图片的话会返回"非图片，不可预览。文件路径为：+文件路径"
     */
//    public static String upLoad(File file) {
//        // 默认值为：true
//        boolean isImage = true;
//        // 判断所要上传的图片是否是图片，图片可以预览，其他文件不提供通过URL预览
//        try {
//            Image image = ImageIO.read(file);
//            isImage = image != null;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log.info("------OSS文件上传开始--------" + file.getName());
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = format.format(new Date());
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        try {
//            // 判断容器是否存在,不存在就创建
//            if (!ossClient.doesBucketExist(bucketName)) {
//                ossClient.createBucket(bucketName);
//                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//                ossClient.createBucket(createBucketRequest);
//            }
//            // 设置文件路径和名称
//            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
//            if (isImage) {//如果是图片，则图片的URL为：....
//                FILE_URL = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
//            } else {
//                FILE_URL = "非图片，不可预览。文件路径为：" + fileUrl;
//            }
//
//            // 上传文件
//            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
//            // 设置权限(公开读)
//            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//            if (result != null) {
//                log.info("------OSS文件上传成功------" + fileUrl);
//            }
//        } catch (OSSException oe) {
//            log.error(oe.getMessage());
//        } catch (ClientException ce) {
//            log.error(ce.getErrorMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//        return FILE_URL;
//    }

    /**
     * 通过文件名下载文件
     *
     * @param objectName    要下载的文件名
     * @param localFileName 本地要创建的文件名
     */
//    public static void downloadFile(String objectName, String localFileName) {
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
//        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFileName));
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }

    /**
     * 列举 test 文件下所有的文件
     */
//    public static void listFile() {
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        // 构造ListObjectsRequest请求。
//        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
//
//        // 设置prefix参数来获取fun目录下的所有文件。
//        listObjectsRequest.setPrefix("blog/");
//        // 列出文件。
//        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
//        // 遍历所有文件。
//        System.out.println("Objects:");
//        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
//            System.out.println(objectSummary.getKey());
//        }
//        // 遍历所有commonPrefix。
//        System.out.println("CommonPrefixes:");
//        for (String commonPrefix : listing.getCommonPrefixes()) {
//            System.out.println(commonPrefix);
//        }
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }

//    public String uploadFile(InputStream inputStream, String fileUrl) {
//        OSS ossClient = new OSSClientBuilder().build(endpoint,
//                accessKeyId, accessKeySecret);
//        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileUrl, inputStream);
//        return endpoint + File.separator + fileUrl;
//    }

}