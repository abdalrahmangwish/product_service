package com.example.employee_service.services;


import com.example.employee_service.entities.UserServiceClient;
import com.example.employee_service.repositories.ActionRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Autowired
    private ActionRepository employeeRepository;
    @Autowired
    private UserServiceClient userServiceClient;

    public User getUser() {
        User user = userServiceClient.getUserById();
        return user;
    }
}
