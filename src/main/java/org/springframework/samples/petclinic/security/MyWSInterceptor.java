package org.springframework.samples.petclinic.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;

public class MyWSInterceptor implements org.springframework.ws.server.EndpointInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MyWSInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        log.info("handleRequest");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        log.info("handleResponse");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        log.info("handleFault");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {
        log.info("afterCompletion");

    }
}
