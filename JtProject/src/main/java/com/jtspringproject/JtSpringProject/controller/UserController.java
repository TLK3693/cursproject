package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.jtspringproject.JtSpringProject.services.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.cartService;

import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    private userService userService;

    @Autowired
    private productService productService;

    @RequestMapping(value = {"/logout"})
    public String returnIndex() {
        return "userLogin";
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    @GetMapping("/")
    public String userlogin(Model model) {

        return "userLogin";
    }

    @RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
    public ModelAndView userlogin(@RequestParam("username") String username, @RequestParam("password") String pass, Model model, HttpServletResponse res) {

        System.out.println(pass);
        User u = this.userService.checkLogin(username, pass);
        System.out.println(u.getUsername());

        if (username.equals(u.getUsername())) {

            res.addCookie(new Cookie("username", u.getUsername()));
            ModelAndView mView = new ModelAndView("index");
            mView.addObject("user", u);
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("msg", "Нет товаров в наличии");
            } else {
                mView.addObject("products", products);
            }
            return mView;

        } else {
            ModelAndView mView = new ModelAndView("userLogin");
            mView.addObject("msg", "Пожалуйста, введите правильный адрес электронной почты и пароль");
            return mView;
        }
    }

    @GetMapping("/user/products")
    public ModelAndView getproduct() {

        ModelAndView mView = new ModelAndView("uproduct");

        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mView.addObject("msg", "Нет товаров в наличии");
        } else {
            mView.addObject("products", products);
        }

        return mView;
    }

    @RequestMapping(value = "newuserregister", method = RequestMethod.POST)
    public ModelAndView newUseRegister(@ModelAttribute User user) {
        // Check if username already exists in database
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            System.out.println(user.getEmail());
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);

            System.out.println("Новый пользователь: " + user.getUsername());
            ModelAndView mView = new ModelAndView("userLogin");
            return mView;
        } else {
            System.out.println("Имя занято - попробуйте другой: " + user.getUsername());
            ModelAndView mView = new ModelAndView("register");
            mView.addObject("msg", user.getUsername() + " Имя занято - попробуйте другой");
            return mView;
        }
    }

    //	@GetMapping("carts")
    //	public ModelAndView  getCartDetail()
    //	{
    //		ModelAndView mv= new ModelAndView();
    //		List<Cart>carts = cartService.getCarts();
    //	}

    @GetMapping("/profileDisplay")
    public ModelAndView display() {
        ModelAndView mView = new ModelAndView("updateProfile");
        //User u= this.userService.getCurrentUser();
        //String name= u.getUsername();
        //System.out.println(name);
        //mView.addObject("username","lisa");
        return mView;
    }
}
