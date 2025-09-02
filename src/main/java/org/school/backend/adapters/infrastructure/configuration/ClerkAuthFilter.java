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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class ClerkAuthFilter implements Filter {

    private final String clerkSecretKey;

    public ClerkAuthFilter(@Value("${clerk.secret-key}") String clerkSecretKey) {
        this.clerkSecretKey = clerkSecretKey;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


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
            res.getWriter().write(String.format(
                    "{\"error\": \"Unauthorized\", \"reason\": \"%s\"}",
                    requestState.reason()
            ));
        }
    }
}