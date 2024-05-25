package com.example.employee_service.services;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = jwtTokenProvider.getToken();
        if (token != null) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}
