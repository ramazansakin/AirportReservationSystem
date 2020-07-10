package com.sakinr.airportreservationsystem.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(2)
public class RequestResponseAuditFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info(
                "Incoming Request  {} : {}", req.getMethod(),
                req.getRequestURI()
        );
        chain.doFilter(request, response);
        log.info(
                "Outgoing Response :{}",
                res.getContentType()
        );
        chain.doFilter(request, response);
    }
}
