package com.Controller;

import com.Service.UserService;
import com.people.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/my")
@CrossOrigin(origins = "http://localhost:5173")
public class MyController {
    @Autowired
    private UserService userService;

    // 🔎 获取当前用户信息
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = Optional.ofNullable(userService.getUserByUsername(username));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✏️ 更新用户信息
    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User newUser) {
        Optional<User> existingUser = Optional.ofNullable(userService.getUserByUsername(username));
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在");
        }

        User user = existingUser.get();
        if (newUser.getPhone() != null) user.setPhone(newUser.getPhone());
        if (newUser.getPassword() != null) user.setPassword(newUser.getPassword());
        if (newUser.getAvatar() != null) user.setAvatar(newUser.getAvatar());

        userService.updateUser(user);
        return ResponseEntity.ok("个人信息更新成功！");
    }
}

