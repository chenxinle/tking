package com.me.tking.controller;

import com.me.tking.dao.UserDao;
import com.me.tking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseController.PREFIX + "/user")
public class UserRestController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable() int id) {
        return ResponseEntity.ok(
            userService.getUserById(id)
        );
    }
}
