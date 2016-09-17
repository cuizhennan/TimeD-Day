package mkcoding.services.timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mx on 16/9/17.
 */
@RestController
public class DefaultController extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class.getCanonicalName());

    @RequestMapping("/status")
    public String isSuccess() {
        logger.info("health check success.");
        return "SUCCESS";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("init mapping path [/]");
        return application.sources(BootStart.class);
    }
}
