package com.tugas.deploy.Controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASS = "20230140193";
    private List<User> users = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (username.equals(ADMIN_USER) && password.equals(ADMIN_PASS)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password Salah!");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {

        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        users.add(user);
        model.addAttribute("users", users);
        model.addAttribute("success", "Data Mahasiswa Berhasil Disimpan!");
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}