package mkcoding.services.timed.controller;

import mkcoding.services.timed.model.Greeting;
import mkcoding.services.timed.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mx on 16/12/26.
 */
@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class.getCanonicalName());

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(2000);
        logger.info("websocket send :" + message.getName());
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
