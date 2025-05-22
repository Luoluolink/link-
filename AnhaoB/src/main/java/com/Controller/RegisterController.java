package com.Controller;

import com.Service.UserService;
import com.people.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);
        return isRegistered
                ?ResponseEntity.ok("恭喜你注册成功")
                :ResponseEntity.status(400).body("❌ 注册失败，用户已存在！");
    }
}
