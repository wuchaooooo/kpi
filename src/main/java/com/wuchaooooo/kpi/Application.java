package com.wuchaooooo.kpi;

import com.wuchaooooo.kpi.utils.AuthUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by wuchaooooo on 11/11/2016.
 */
@SpringBootApplication
public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//        AuthUtils.setApplicationContext(applicationContext);
        logger.info("SpringBoot Start Success");
    }
}
