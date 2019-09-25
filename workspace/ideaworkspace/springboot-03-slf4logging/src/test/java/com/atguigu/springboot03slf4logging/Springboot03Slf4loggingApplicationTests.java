package com.atguigu.springboot03slf4logging;

import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03Slf4loggingApplicationTests {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoads() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }

}
