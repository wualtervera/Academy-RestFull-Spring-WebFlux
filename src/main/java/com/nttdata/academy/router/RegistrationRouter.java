package com.nttdata.academy.router;

import com.nttdata.academy.handler.RegistrationHandler;
import com.nttdata.academy.model.entity.Registration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RegistrationRouter {
    String uri = "api/v1/registration";

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/registration",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = RegistrationHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(
                            operationId = "findAll",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Registration.class
                                            )))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/registration/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = RegistrationHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId = "findById",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Registration.class
                                            ))),
                                    @ApiResponse(responseCode = "404", description = "Not found")
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            })
            ),
            @RouterOperation(
                    path = "/api/v1/registration",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = RegistrationHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Registration.class
                                            ))
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Registration.class
                                    ))
                            ))
            ),
            @RouterOperation(
                    path = "/api/v1/registration/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.PUT,
                    beanClass = RegistrationHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "update",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Registration.class
                                            )))
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Registration.class
                                    ))
                            ),
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            }

                    )

            ),
            @RouterOperation(path = "/api/v1/registration/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.DELETE,
                    beanClass = RegistrationHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(operationId = "delete",
                            responses = {
                                    @ApiResponse(responseCode = "204", description = "No content"),
                                    @ApiResponse(responseCode = "404", description = "Not found")
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> registrationRouters(RegistrationHandler handler){
        return RouterFunctions.route(RequestPredicates.GET(uri), handler::findAll)
                .andRoute(RequestPredicates.GET(uri.concat("/{id}")), handler::findById)
                .andRoute(RequestPredicates.POST(uri), handler::save)
                .andRoute(RequestPredicates.PUT(uri.concat("/{id}")), handler::update)
                .andRoute(RequestPredicates.DELETE(uri.concat("/{id}")), handler::delete)
                .andRoute(RequestPredicates.GET(uri.concat("/student/{id}")), handler::findByIdStudent);
    }
}
