package org.example.service;

import org.example.pojo.Result;
import org.example.pojo.User;

public interface IUserService {
    Result login(User user);

    Result getUserInfo();
}
