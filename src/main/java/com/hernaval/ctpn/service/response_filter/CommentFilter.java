package com.hernaval.ctpn.service.response_filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hernaval.ctpn.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentFilter {

    private static final Logger log = LoggerFactory.getLogger(CommentFilter.class);

    /**
     * @param objectMapper
     * check if client is authenticated
     * else remove commenters object from json response
     */
    public CommentFilter(ObjectMapper objectMapper) {
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);

        PropertyFilter filter = new SimpleBeanPropertyFilter() {
            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }

            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }

            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
                throws Exception {
                if (include(writer)) {
                    if (!writer.getName().equals("commenters")) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }

                    //check client auth status
                    boolean isAuthenticated = SecurityUtils.isAuthenticated();
                    if (!isAuthenticated) {
                        log.warn("Anonymous user is attempted request, filter response imediatly");
                    }
                    //add commenters object only if client is authenticated
                    if (isAuthenticated) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                } else if (!jgen.canOmitFields()) { // since 2.3
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }
        };
        simpleFilterProvider.addFilter("commentFilterOnAnonymousClient", filter);

        objectMapper.setFilterProvider(simpleFilterProvider);
    }
}
