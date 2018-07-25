package org.springframework.samples.petclinic.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringDigestPasswordValidationCallbackHandler;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;

import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWs
public class WsSecurity extends WsConfigurerAdapter {

    @Value("classpath:securityPolicy.xml")
    private Resource securityPolicy;

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(new MyWSInterceptor());
        PayloadLoggingInterceptor loggingInterceptor = new PayloadLoggingInterceptor();
        loggingInterceptor.setLogRequest(true);
        interceptors.add(loggingInterceptor);
        //interceptors.add(wsSecurityInterceptor());
    }

    //@Bean("wsSecurityInterceptor")
    XwsSecurityInterceptor wsSecurityInterceptor() {
        XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();
        interceptor.setPolicyConfiguration(securityPolicy);
        interceptor.setCallbackHandlers(new CallbackHandler[]{handler()});
        return interceptor;
    }

    SpringPlainTextPasswordValidationCallbackHandler handler(){
        return new SpringPlainTextPasswordValidationCallbackHandler();
    }


}
