package com.bapp.user.management.config;

import com.bapp.user.management.controller.HelloController;
import com.bapp.user.management.controller.UserController;
import com.bapp.user.management.repository.InMemoryUserRepository;
import com.bapp.user.management.repository.UserRepository;
import com.bapp.user.management.service.user.DefaultUserService;
import com.bapp.user.management.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.bapp.user.management.controller.UserController.Links.USER_URL;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class ControllerConfig {

    @Value("${foo}")
    private String test;

    @Bean
    public UserRepository userRepository() {
        log.info(">>> foo = " + test);
        return new InMemoryUserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new DefaultUserService(userRepository);
    }

    @Bean
    public UserController userController(UserService userService) {
        return new UserController(userService);
    }

    @Bean
    public RouterFunction<ServerResponse> userControllerRouterFunction(UserController userController) {
        return route(POST(USER_URL), userController::createUser)
                .and(route(GET(USER_URL), userController::getAll));
    }

    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello"), req -> new HelloController().sayHello(req));
    }

}
