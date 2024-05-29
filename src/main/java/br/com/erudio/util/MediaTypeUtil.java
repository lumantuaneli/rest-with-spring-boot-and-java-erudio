package br.com.erudio.util;

import org.springframework.http.MediaType;

public class MediaTypeUtil {
    
    public static final String APPLICATION_JSON_EXTENSION = "json";
    
    public static final String APPLICATION_XML_EXTENSION = "xml";
    
    public static final String APPLICATION_YML_VALUE = "application/x-yaml";
    public static final MediaType APPLICATION_YML = MediaType.valueOf(APPLICATION_YML_VALUE);
    public static final String APPLICATION_EXTENSION_YML = "yml";
    
    private MediaTypeUtil() {
        throw new UnsupportedOperationException();
    }
}
