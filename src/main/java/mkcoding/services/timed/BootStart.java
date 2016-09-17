package mkcoding.services.timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by mx on 16/3/20.
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = DefaultController.class)
public class BootStart {
    private static final Logger logger = LoggerFactory.getLogger(BootStart.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStart.class, args);
    }
}
