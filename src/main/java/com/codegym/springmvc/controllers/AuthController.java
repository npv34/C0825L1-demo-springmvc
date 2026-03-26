package com.codegym.springmvc.controllers;

import com.codegym.springmvc.request.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // Xu ly request "/auth/login" voi method GET
    @GetMapping("/login")
    public String login(Model model,
                        @CookieValue(value = "username", defaultValue = "") String username) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        model.addAttribute("loginRequest", request);
        return "auth/login";
    }

    // Xu ly request "/auth/login" voi method POST
    @PostMapping("/login")
    public String submitLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest,
                              HttpServletResponse response, HttpSession session) {

        // xu ly login
        // Neu login thanh cong luu thong tin dang nhap vao session
        if ("luanphan".equals(loginRequest.getUsername()) && "123456".equals(loginRequest.getPassword())) {
            // login success -> Luu username -> session
            session.setAttribute("USER_NAME_SESSION", loginRequest.getUsername());
            if (loginRequest.isRememberMe()) {
                // tao cookie luu thong tin dang nhap
                Cookie cookie = new Cookie("username", loginRequest.getUsername());
                cookie.setMaxAge(7 * 24 * 60 * 60); // luu trong 7 ngay
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                // xoa cookie dang nhap
                Cookie cookie = new Cookie("username", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return "redirect:/users";
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USER_NAME_SESSION");
        return "redirect:/auth/login";
    }
}
