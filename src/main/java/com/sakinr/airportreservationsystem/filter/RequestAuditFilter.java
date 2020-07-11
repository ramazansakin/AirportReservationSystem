package com.sakinr.airportreservationsystem.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@Order(2)
public class RequestAuditFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info(
                "Incoming Request  {} : {}",
                req.getMethod(),
                req.getRequestURI()
        );
        chain.doFilter(request, response);
    }
}
