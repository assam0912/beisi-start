package org.example.controller;

import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/api")
public class UserController {

    @Autowired
    IUserService userService;

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    // 获取用户信息
    @GetMapping("/userInfo")
    public Result userInfo() {
        return userService.getUserInfo();
    }
}
