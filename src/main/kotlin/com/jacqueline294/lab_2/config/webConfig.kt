package com.jacqueline294.lab_2.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Apply to all endpoints
            .allowedOrigins("http://localhost:3001") // Allow specific origin
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
            .allowedHeaders("*") // Allowed headers
    }

}
