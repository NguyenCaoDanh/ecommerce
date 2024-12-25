package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RequestResponse;
import com.example.ecommerce.dto.request.LoginDTO;
import com.example.ecommerce.dto.response.Token;
import com.example.ecommerce.entity.Users;
import com.example.ecommerce.exception.ExceptionResponse;
import com.example.ecommerce.generic.GenericController;
import com.example.ecommerce.generic.IService;
import com.example.ecommerce.jwt.JwtService;
import com.example.ecommerce.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/account")
public class AccountController extends GenericController<Users, Integer> {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public IService<Users, Integer> getService() {
        return accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                new RequestResponse(
                                        new Token(jwtService.generateToken(loginDTO.getUsername()))));
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage()));
        }
    }

    @PostMapping("/regis")
    public ResponseEntity<?> add(
            @RequestParam("account") String accountJson,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        try {
            // Parse JSON từ chuỗi
            ObjectMapper objectMapper = new ObjectMapper();
            Users account = objectMapper.readValue(accountJson, Users.class);

            // Xử lý upload avatar nếu có
            if (avatar != null && !avatar.isEmpty()) {
                String fileName = StringUtils.cleanPath(avatar.getOriginalFilename());
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

                Path uploadPath = Paths.get(uploadDir);

                // Tạo thư mục nếu chưa tồn tại
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu file
                Path filePath = uploadPath.resolve(fileName);
                avatar.transferTo(filePath.toFile());

                // Lưu đường dẫn file vào database
                account.setAvatar("/images/" + fileName);
            }

            // Lưu thông tin người dùng
            accountService.save(account);

            return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse("Register successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse("Error: " + e.getMessage()));
        }
    }


}
