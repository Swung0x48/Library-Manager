package com.swung0x48.librarymanager.filter;

import com.swung0x48.librarymanager.RuntimeConstants;
import com.swung0x48.librarymanager.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null) {
            String[] authHeaderArray = authHeader.split("Bearer ");
            if (authHeaderArray.length > 1 && authHeaderArray[1] != null) {
                String token = authHeaderArray[1];

                try {
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(RuntimeConstants.API_SECRET_KEY)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

                    httpServletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                    if (!claims.get("role").equals("ADMIN")) {
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not an admin");
                        return;
                    }

                    httpServletRequest.setAttribute("role", claims.get("role"));
                } catch (Exception e) {
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
                    return;
                }
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Token ill-formed");
                return;
            }
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
