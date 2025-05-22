package com.Service;

import com.Mapper.UserMapper;
import com.people.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean validateUser(String username, String password) {
        User user = userMapper.findUser(username, password);
        return user != null;
    }

    public boolean registerUser(User user) {
        if(userMapper.findByUsername(user.getUsername()) != null) {
            return false;
        }
        userMapper.insertUser(user);
        return true;
    }
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    public void updateUserAvatar(String username, String avatar) {
        User user = userMapper.findByUsername(username);
        if (user != null) {
            userMapper.updateAvatar(username, avatar); // ✅ 调用 Mapper 更新数据库
        }
    }
}