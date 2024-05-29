package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;
import br.com.erudio.util.MediaTypeUtil;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer pConfigurer) {
        pConfigurer
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType(MediaTypeUtil.APPLICATION_JSON_EXTENSION, MediaType.APPLICATION_JSON)
            .mediaType(MediaTypeUtil.APPLICATION_XML_EXTENSION, MediaType.APPLICATION_XML)
            .mediaType(MediaTypeUtil.APPLICATION_EXTENSION_YML, MediaTypeUtil.APPLICATION_YML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> pConverters) {
        pConverters.add(new YamlJackson2HttpMessageConverter());
    }

}
