package com.example.employee_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    @Value("${jwt.header}")
    private String header;

    @Autowired
    private HttpServletRequest request;

    public String getToken() {
        String token = request.getHeader(header);
        if (StringUtils.hasText(token) && token.startsWith(tokenPrefix)) {
            return token.substring(tokenPrefix.length()).trim();
        }
        return null;
    }
}
