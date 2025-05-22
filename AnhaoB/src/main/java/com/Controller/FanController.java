package com.Controller;

import com.Mapper.UserMapper;
import com.people.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FanController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/addFan")
    public ResponseEntity<String> addFan(@RequestBody Map<String, String> requestData) {
        String username = requestData.get("username");
        String fanTable = requestData.get("fanTable"); // 目标表名，例如 "kanafans"

        // 查询用户完整信息
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户不存在");
        }

        // 插入数据库
        int result = userMapper.addUserToFansTable(user.getUsername(), user.getAvatar(), user.getPhone(), fanTable);

        if (result > 0) {
            return ResponseEntity.ok("成功加入 " + fanTable + " 粉丝列表！");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("加入失败");
        }
    }

}

