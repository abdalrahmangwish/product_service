package com.example.employee_service.controllers;


import com.example.employee_service.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>("hiiiiiiiiii", HttpStatus.OK);
    }
    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(actionService.getUser(), HttpStatus.OK);
    }
}
