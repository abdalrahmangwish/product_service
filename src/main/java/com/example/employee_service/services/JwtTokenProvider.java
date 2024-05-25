package com.example.employee_service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Value("${auth.service.username}")
    private String username;

    @Value("${auth.service.password}")
    private String password;

    private String jwtToken;

    @Autowired
    private RestTemplate restTemplate;

    public String getToken() {
        if (jwtToken == null || isTokenExpired(jwtToken)) {
            jwtToken = fetchTokenFromAuthService();
        }
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmRhbHJhaG1hbiIsImlhdCI6MTcxNjY0MzQ1NiwiZXhwIjoxNzE2Njc5NDU2fQ.EqKk5irpeZF1ZZ3M49YaEnIc9_zaKHUn2RZyIoEDJPY";
    }

    private String fetchTokenFromAuthService() {
        try {
            AuthRequest authRequest = new AuthRequest(username, password);
            logger.info("Sending authentication request to {}", authServiceUrl);
            AuthResponse authResponse = restTemplate.postForObject(authServiceUrl, authRequest, AuthResponse.class);
            if (authResponse != null) {
                logger.info("Received JWT token: {}", authResponse.getJwt());
                return authResponse.getJwt();
            } else {
                logger.warn("Received null response from auth service");
            }
        } catch (Exception e) {
            logger.error("Error fetching token from auth service", e);
        }
        return null;
    }

    private boolean isTokenExpired(String token) {
        // Implement token expiration logic here
        return false;
    }

    // Inner classes for request and response
    public static class AuthRequest {
        private String username;
        private String password;

        public AuthRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthResponse {
        private String jwt;

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }
}
