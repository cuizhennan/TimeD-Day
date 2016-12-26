package mkcoding.services.timed;

import mkcoding.services.timed.commons.Error;
import mkcoding.services.timed.commons.TimedExcepion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @RequestMapping("/get/{id}")
    public String testRespStatus(@PathVariable("id") String id) {
        if (id.equals("1")) {
            throw new TimedExcepion(Long.valueOf(id));
        }

        return id;
    }

    @RequestMapping("/post/{id}")
    public ResponseEntity<String> testReqPost(@PathVariable("id") String id, UriComponentsBuilder ucb) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = ucb.path("/get/" + 2).build().toUri();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<String>(id, httpHeaders, HttpStatus.CREATED);
    }

    @ExceptionHandler(TimedExcepion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error execptionGlobal(TimedExcepion e) {
        return new Error(4, "id " + e.getId() + " Not Found");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("init mapping path [/]");
        return application.sources(BootStart.class);
    }
}
