package com.nttdata.academy.router;

import com.nttdata.academy.handler.InstructorCourseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class InstructorCourseRouter {
    String uri = "api/v1/instructorcourse";

    @Bean
    public RouterFunction<ServerResponse> instructorCourseRouters(InstructorCourseHandler handler){
        return RouterFunctions.route(RequestPredicates.GET(uri), handler::findAll)
                .andRoute(RequestPredicates.GET(uri.concat("/{id}")), handler::findById)
                .andRoute(RequestPredicates.POST(uri), handler::save)
                .andRoute(RequestPredicates.PUT(uri.concat("/{id}")), handler::update)
                .andRoute(RequestPredicates.DELETE(uri.concat("/{id}")), handler::delete);
    }


}
