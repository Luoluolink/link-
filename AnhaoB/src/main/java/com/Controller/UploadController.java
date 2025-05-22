package com.Controller;

import com.Service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private UserService userService; // ✅ 调用 UserService，而不是直接用 UserMapper

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/avatar/{username}")
    public ResponseEntity<String> uploadAvatar(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ 文件不能为空");
        }

        try {
            // 🔹 设定 `uploads` 目录的绝对路径
            String uploadDir = "D:/java/AnhaoB/uploads/";
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs(); // ✅ 自动创建 `uploads/` 目录
                System.out.println("✅ 创建 uploads 目录");
            }

            // 🔹 存储文件
            File destinationFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(destinationFile); // ✅ 确保文件存储在 `D:/java/AnhaoB/uploads/`

            // 🔹 生成图片 URL
            String imageUrl = "http://localhost:8080/uploads/" + file.getOriginalFilename();
            userService.updateUserAvatar(username, imageUrl);

            System.out.println("✅ 头像上传成功, URL: " + imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ 上传失败");
        }
    }


}
