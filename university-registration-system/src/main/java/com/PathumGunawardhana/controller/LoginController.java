package com.PathumGunawardhana.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.PathumGunawardhana.dao.StudentDAO;

@Controller
public class LoginController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(HttpServletRequest request, HttpSession session) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int studentId = studentDAO.validateUserAndGetId(email, password);

        if (studentId != -1) {

            session.setAttribute("studentId", studentId);
            session.setAttribute("user", email);

            System.out.println("LOGIN SUCCESS: " + email);

            return "redirect:/courses";

        } else {

            System.out.println("LOGIN FAILED: " + email);

            request.setAttribute("error", "Invalid email or password");

            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}