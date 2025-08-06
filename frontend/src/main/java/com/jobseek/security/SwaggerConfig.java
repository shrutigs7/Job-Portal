package com.jobseek.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

@OpenAPIDefinition(
        info = @Info(
                title = "Job Portal API",
                version = "1.0",
                description = "API documentation for Job Portal System"
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@Configuration
public class SwaggerConfig {
}
