package uite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这是一个单例类
 * 因为用户访问我们项目的目的就是访问对应的数据库
 * 数据库链接四要素 所有用户共享一个足矣
 * 只开辟一个内存空间
 */
public class ConfigManager {
    //    0.1 创建需要单例的静态变量
    private static ConfigManager configManager;
    //创建Properties对象  专门解析properties文件的
    private static Properties properties;

    //0.2 静态代码块 不会实例化 优先上车效率高
    static {
        String path = "jdbc.properties";
        properties = new Properties(); //实例化对象
        InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(path);
        //加载我们自己的properties文件
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //0.3 创建对外访问接口
    public static synchronized ConfigManager getInstance() {
        return configManager;
    }

    /**
     * properties对象已经有值了！properties已经进入了内存！
     * 我们就可以通过key获取value
     */
    public static String getValue(String key) {
//        properties 是一个键值对的文件 根据key获取value
        return properties.getProperty(key);
    }
}
