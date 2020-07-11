package com.sakinr.airportreservationsystem.filter;

import com.sakinr.airportreservationsystem.filter.parser.ByteArrayPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(3)
@Slf4j
public class SwaggerFilter implements Filter {

    final String APPLICATION_XHTML = "application/xhtml";
    final String XML_ELEMENT_START = "<Json>";
    final String XML_ELEMENT_END = "</Json>";

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ByteArrayPrinter pw = new ByteArrayPrinter();
        HttpServletResponse wrappedResp = new HttpServletResponseWrapper(response) {

            @Override
            public void setContentType(final String type) {
                super.setContentType(MediaType.APPLICATION_JSON_VALUE);
            }

            @Override
            public PrintWriter getWriter() {
                return pw.getWriter();
            }

            @Override
            public ServletOutputStream getOutputStream() {
                ServletResponse response = this.getResponse();

                String ct = (response != null) ? response.getContentType() : null;
                if (ct != null && ct.contains(APPLICATION_XHTML)) {
                    response.setContentType(ct + "," + MediaType.APPLICATION_JSON_VALUE);
                }
                return pw.getStream();
            }

        };
        chain.doFilter(httpRequest, wrappedResp);

        byte[] bytes = pw.toByteArray();
        String respBody = new String(bytes);
        if (respBody.startsWith(XML_ELEMENT_START)) {

            List<String> s13 = Stream.of(respBody).filter((s1) -> s1.contains(XML_ELEMENT_START))
                    .map((sample) -> {
                        return Arrays.asList(sample.split(" "));
                    })
                    .flatMap((listString) -> {
                        StringBuilder sb = new StringBuilder();
                        listString.forEach(sb::append);
                        return Stream
                                .of(sb.toString().trim().replace(XML_ELEMENT_START, "").replace(XML_ELEMENT_END, ""));
                    }).collect(Collectors.toList());

            String s14 = String.join("", s13);

            response.getOutputStream().write(s14.getBytes());
        } else {
            response.getOutputStream().write(bytes);
        }
        log.info(
                "Outgoing Response :{}",
                respBody
        );
    }

    @Override
    public void destroy() {

    }

}
