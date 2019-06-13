package com.bapp.user.management.config;

import com.bapp.user.management.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ControllerConfig {


    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return RouterFunctions.route(GET("/hello"), req -> new HelloController().sayHello(req));
    }

}
