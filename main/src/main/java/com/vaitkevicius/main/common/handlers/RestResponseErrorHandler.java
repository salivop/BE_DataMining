package com.vaitkevicius.main.common.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

/**
 * *
 * Created By Povilas 14/11/2018
 * *
 **/
public class RestResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            LOG.error("{} response. Throwing authentication exception", HttpStatus.FORBIDDEN);
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Status code: {}", response.getStatusCode());
            LOG.error("Response {}", response.getStatusText());
            LOG.error("Message {}", response.getHeaders());

            if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
                LOG.error("Call returned a error 403 forbidden resposne ");
                return true;
            }
        }
        return false;
    }
}

