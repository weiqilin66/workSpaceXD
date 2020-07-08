package org.wayne.cloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description: ConfigServer
 * @author: LinWeiQi
 */
@SpringBootApplication
@EnableConfigServer //开启ConfigServer功能
public class SpringCloudConfigApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApp.class,args);
    }
}
