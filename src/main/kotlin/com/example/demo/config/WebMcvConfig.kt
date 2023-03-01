package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver
import java.io.IOException
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(object : HandlerInterceptor {
            override fun afterCompletion(
                request: HttpServletRequest,
                response: HttpServletResponse,
                handler: Any,
                ex: Exception?
            ) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0")
                response.setHeader("Pragma", "no-cache")
                response.setHeader("Expires", "0")
            }
        })
    }
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/app/**")
            .addResourceLocations("classpath:/static/")
            .resourceChain(true)
            .addResolver(object : PathResourceResolver() {
                @Throws(IOException::class)
                override fun getResource(
                    resourcePath: String,
                    location: Resource
                ): Resource {
                    val requestedResource: Resource = location.createRelative(resourcePath)
                    return if (requestedResource.exists() && requestedResource.isReadable) {
                        requestedResource
                    } else {
                        ClassPathResource(
                            "/static/index.html"
                        )
                    }
                }
            })
    }
}