package center.helloworld;

import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentials;
import com.aliyuncs.auth.DefaultCredentialsProvider;

/**
 * @author zhishun.cai
 * @date 2024/11/1
 */
public class CustomCredentialProvider {

  public static CredentialsProvider getCredentialsProvider() {

    return new CredentialsProvider() {

      // 初始化变量
      String accessKeyId = "LTAI5tJmwVbTX6w8UCLBmvrm";
      // 初始化变量
      String accessKeySecrect = "MrExNENvj2qfM6zPX56nZNNLrVZXZi";
      // 初始化变量
      // String token = null;
      @Override
      public void setCredentials(Credentials credentials) {

      }

      @Override
      public Credentials getCredentials() {
        //TODO
        //自定义访问凭证的获取方法

        // 返回长期凭证 access_key_id, access_key_secrect
        return new DefaultCredentials(accessKeyId, accessKeySecrect);

        // 返回 临时凭证 access_key_id, access_key_secrect, token
        // 对于临时凭证，需要根据过期时间，刷新凭证。
        // return new DefaultCredentials(accessKeyId, accessKeySecrect, token);
      }
    };
  }
}
