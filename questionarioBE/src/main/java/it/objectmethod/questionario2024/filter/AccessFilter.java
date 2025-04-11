package it.objectmethod.questionario2024.filter;

import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AccessFilter extends OncePerRequestFilter {

    private static final String POST = "POST";
    private static final String AUTH_URI = "/auth";
    @Autowired
    private BearerTokenUtil bearerUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String jwtToken = request.getHeader("Authorization");
        String requestURI = request.getRequestURI() != null ? request.getRequestURI() : "";

        if (!requestURI.startsWith(AUTH_URI)) {
            if (Objects.isNull(jwtToken) || !isAuthenticated(jwtToken)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthenticated(final String jwtToken) {
        return bearerUtil.isTokenValid(jwtToken);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return request.getRequestURI().startsWith(AUTH_URI);
    }

}