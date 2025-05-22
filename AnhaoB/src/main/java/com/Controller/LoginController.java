package com.Controller;
import com.Mapper.UserMapper;
import com.people.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = userMapper.findByUsername(user.getUsername());

        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }

        return ResponseEntity.ok(dbUser); // 直接返回用户信息
    }
}

