// ClerkAuthFilter.java

package org.school.backend.adapters.infrastructure.configuration;

import com.clerk.backend_api.helpers.security.AuthenticateRequest;
import com.clerk.backend_api.helpers.security.models.AuthenticateRequestOptions;
import com.clerk.backend_api.helpers.security.models.RequestState;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class ClerkAuthFilter implements Filter {

    private final String clerkSecretKey;
    private final LoggerGateway logger;

    public ClerkAuthFilter(
            @Value("${clerk.secret-key}") String clerkSecretKey,
            LoggerGateway logger
    ) {
        this.clerkSecretKey = clerkSecretKey;
        this.logger = logger;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

//        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
//            res.setStatus(HttpServletResponse.SC_OK);
//            res.setHeader("Access-Control-Allow-Origin", "*");
//            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//            return;
//        }

        Map<String, List<String>> headers = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, Collections.list(req.getHeaders(name)));
        }

        RequestState requestState = AuthenticateRequest.authenticateRequest(
                headers,
                AuthenticateRequestOptions.secretKey(clerkSecretKey).build()
        );

        if (requestState.isSignedIn()) {
            req.setAttribute("clerkUserId", requestState.getClass().getSigners());
            chain.doFilter(request, response);
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setHeader("Access-Control-Allow-Origin", "*");
            String payload = String.format(
                    "{\"error\": \"Unauthorized\", \"reason\": \"%s\"}",
                    requestState.reason()
            );
            logger.warn(payload);
            res.getWriter().write(payload);
        }
    }

}