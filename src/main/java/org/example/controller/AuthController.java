package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.auth.UserCreateDto;
import org.example.dto.auth.UserLoginDTO;
import org.example.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 20:13 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(value = "/login")
    public String loginPage(){
        return "auth/login";
    }

    @PostMapping(value = "/login")
    public void login(@ModelAttribute UserLoginDTO userLoginDTO, HttpServletResponse response) throws IOException {
       Long userId=authService.login(userLoginDTO);
        Cookie cookie=new Cookie("userId",String.valueOf(userId));
        response.addCookie(cookie);
        response.sendRedirect("/");
    }
    @PostMapping(value = "/register")
    public void register(@ModelAttribute UserCreateDto userCreateDto,HttpServletResponse response) throws IOException {
        Long userId=authService.create(userCreateDto);
        Cookie cookie=new Cookie("userId",String.valueOf(userId));
        response.addCookie(cookie);
        response.sendRedirect("/");
    }
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request){
    }
}
