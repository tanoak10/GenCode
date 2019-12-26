package com.tanoak.utils;

import com.tanoak.entity.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.URL;

/**
 * @author 三木
 * @date 2019/12/23
 * 配置类工具
 */
public class ConfigUtil {
    private static Configuration configuration;

    static {
        URL url = ConfigUtil.class.getClassLoader().getResource("generator.yml");
        assert url != null;
        if (url.getPath().contains("jar")) {
//            用户未提供配置文件
            System.err.println("Can not find file named 'generator.yml' at resources path, please make sure that you have defined that file.");
            System.exit(0);
        } else {
            InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("generator.yml");
            Yaml yaml = new Yaml();

            configuration = yaml.loadAs(inputStream,Configuration.class);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

}
