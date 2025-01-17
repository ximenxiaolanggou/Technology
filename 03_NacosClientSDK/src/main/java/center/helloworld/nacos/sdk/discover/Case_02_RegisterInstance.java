package center.helloworld.nacos.sdk.discover;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.Properties;

/**
 * 注册实例
 *
 * @author zhishun.cai
 * @date 2024/12/13
 */
public class Case_02_RegisterInstance {

    public static final String HOST = "nacos.helloworld.center:8848";

    public static void main(String[] args) throws NacosException, IOException {


        Properties properties = new Properties();
        // 指定Nacos-Server的地址
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, HOST);
        //指定Nacos-SDK的命名空间
        properties.setProperty(PropertyKeyConst.NAMESPACE, "test");
        // 用户名
        properties.setProperty(PropertyKeyConst.USERNAME, "nacos");
        // 密码
        properties.setProperty(PropertyKeyConst.PASSWORD, "nacos");
        // 命名空间
        properties.setProperty(PropertyKeyConst.NAMESPACE, "test");

        // 初始化配置中心的Nacos Java SDK
        ConfigService configService = NacosFactory.createConfigService(properties);

        NamingService namingService = NamingFactory.createNamingService(properties);


        Instance instance = new Instance();
        instance.setIp("127.0.0.1");
        instance.setPort(8848);
        namingService.registerInstance("netty", "127.0.0.1", 8081);

        System.in.read();
    }
}
