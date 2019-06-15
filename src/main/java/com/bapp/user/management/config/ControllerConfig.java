package com.bapp.user.management.config;

import com.bapp.user.management.controller.HelloController;
import com.bapp.user.management.controller.UserController;
import com.bapp.user.management.repository.InMemoryUserRepository;
import com.bapp.user.management.service.user.DefaultUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.bapp.user.management.controller.UserController.Links.USER_URL;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ControllerConfig {

//    @Bean
//    public UserController userController(UserService userService) {
//        return new UserController(userService);
//    }
//
//    @Bean
//    public UserService userService() {
//        return new DefaultUserService(new InMemoryUserRepository());
//    }

    @Bean
    public RouterFunction<ServerResponse> userController() {
        UserController userController = new UserController(new DefaultUserService(new InMemoryUserRepository()));
        return route(POST(USER_URL), userController::createUser)
                .and(route(GET(USER_URL), userController::getAll));
    }

    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello"), req -> new HelloController().sayHello(req));
    }

}
