package org.example.service.impl;

import org.example.pojo.Result;
import org.example.pojo.Token;
import org.example.pojo.User;
import org.example.service.IUserService;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    private final StringRedisTemplate stringRedisTemplate;

    public UserServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 登录
    @Override
    public Result login(User user) {
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 登录成功
            // 根据用户信息生成token
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            // 把token存储到redis中
            stringRedisTemplate.opsForValue().set(token, token, 1, TimeUnit.HOURS);
            return Result.success("Login successful", new Token(token));
        }
        return Result.error("Invalid username or password");
    }

    // 获取用户信息
    @Override
    public Result getUserInfo() {
        Map<String ,Object > map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        return Result.success("获取用户信息成功", new User(username, ""));
    }
}
