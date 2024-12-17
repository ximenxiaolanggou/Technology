package center.helloworld.chapter_02_object;

import center.helloworld.CustomCredentialProvider;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 *
 * 对象存储
 * @author zhishun.cai
 * @date 2024/11/1
 */
public class Object {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。
    private final String endpoint = "https://oss-cn-shanghai.aliyuncs.com";

    private final String region = "cn-shanghai";

    private CredentialsProvider credentialsProvider = CustomCredentialProvider.getCredentialsProvider();

    /**
     * 创建桶
     * @throws Exception
     */
    @Test
    public void createBucket() throws Exception {

        // 填写Bucket名称，例如examplebucket。
        String bucketName = "prinfo";
        // 指定前缀，例如exampledir/object。
        String keyPrefix = "exampledir/object";
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 列举文件。如果不设置keyPrefix，则列举存储空间下的所有文件。如果设置keyPrefix，则列举包含指定前缀的文件。
//            ObjectListing objectListing = ossClient.listObjects(bucketName, keyPrefix);
            ObjectListing objectListing = ossClient.listObjects(bucketName);
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (Exception ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }




    public void printOSSException(OSSException oe) {
        System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        System.out.println("Error Message:" + oe.getErrorMessage());
        System.out.println("Error Code:" + oe.getErrorCode());
        System.out.println("Request ID:" + oe.getRequestId());
        System.out.println("Host ID:" + oe.getHostId());
    }
}
