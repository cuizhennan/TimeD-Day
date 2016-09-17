package mkcoding.services.timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mx on 16/3/20.
 */

@SpringBootApplication
@RestController
public class BootStart extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BootStart.class);

    @RequestMapping("/status")
    public String isSuccess() {
        logger.info("health check SUCCESS");
        return "SUCCESS";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootStart.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BootStart.class, args);
    }
}
