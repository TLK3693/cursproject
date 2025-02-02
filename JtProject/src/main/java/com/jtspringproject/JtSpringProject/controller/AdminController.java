package com.jtspringproject.JtSpringProject.controller;

import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import com.mysql.cj.protocol.Resultset;

import net.bytebuddy.asm.Advice.This;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private userService userService;
	@Autowired
	private categoryService categoryService;
	
	@Autowired
	private productService productService;
	
	int adminlogcheck = 0;

	String usernameforclass = "";

	@RequestMapping(value = {"/","/logout"})
	public String returnIndex() {
		adminlogcheck =0;
		usernameforclass = "";
		return "userLogin";
	}

	@GetMapping("/index")
	public String index(Model model) {
//		if(usernameforclass.equalsIgnoreCase(""))
		if("".equalsIgnoreCase(usernameforclass))
			return "userLogin";
		else {
			model.addAttribute("username", usernameforclass);
			return "index";
		}
			
	}
	
	@GetMapping("login")
	public String adminlogin() {
		
		return "adminlogin";
	}

	@GetMapping("Dashboard")
	public String adminHome(Model model) {
		if(adminlogcheck==1)
			return "adminHome";
		else
			return "redirect:/admin/login";
	}

	@GetMapping("/loginvalidate")
	public String adminlog(Model model) {
		return "adminlogin";
	}

	@RequestMapping(value = "loginvalidate", method = RequestMethod.POST)
	public ModelAndView adminlogin( @RequestParam("username") String username, @RequestParam("password") String pass) {
		
		User user=this.userService.checkLogin(username, pass);
		
		if(user.getRole() != null && user.getRole().equals("ROLE_ADMIN")) {
			ModelAndView mv = new ModelAndView("adminHome");
			adminlogcheck=1;
			mv.addObject("admin", user);
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("adminlogin");
			mv.addObject("msg", "Пожалуйста, введите правильное имя пользователя и пароль");
			return mv;
		}
	}

	@GetMapping("categories")
	public ModelAndView getcategory() {
		if(adminlogcheck==0){
			ModelAndView mView = new ModelAndView("adminlogin");
			return mView;
		}
		else {
			ModelAndView mView = new ModelAndView("categories");
			List<Category> categories = this.categoryService.getCategories();
			mView.addObject("categories", categories);
			return mView;
		}
	}
	@RequestMapping(value = "categories",method = RequestMethod.POST)
	// 	@PostMapping("/categories")
	public String addCategory(@RequestParam("categoryname") String category_name)
	{
		System.out.println(category_name);
		
		Category category =  this.categoryService.addCategory(category_name);
		if(category.getName().equals(category_name)) {
			return "redirect:categories";
		}else {
			return "redirect:categories";
		}
	}

	@GetMapping("categories/delete")
	public ModelAndView removeCategoryDb(@RequestParam("id") int id)
	{
			this.categoryService.deleteCategory(id);
			ModelAndView mView = new ModelAndView("redirect:/admin/categories");
			return mView;
	}

	@GetMapping("categories/update")
	public String updateCategory(@RequestParam("category_id") int id, @RequestParam("categoryname") String categoryname)
	{
		Category category = this.categoryService.updateCategory(id, categoryname);
		return "redirect:/admin/categories";
	}

	
//	 --------------------------Remaining --------------------
	@GetMapping("products")
	public ModelAndView getproduct() {
		if(adminlogcheck==0){
			ModelAndView mView = new ModelAndView("adminlogin");
			return mView;
		}
		else {
			ModelAndView mView = new ModelAndView("products");

			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "Нет товаров в наличии");
			} else {
				mView.addObject("products", products);
			}
			return mView;
		}

	}

	@GetMapping("products/add")
	public ModelAndView addProduct() {
		ModelAndView mView = new ModelAndView("productsAdd");
		List<Category> categories = this.categoryService.getCategories();
		mView.addObject("categories",categories);
		return mView;
	}

	@RequestMapping(value = "products/add",method=RequestMethod.POST)
	public String addProduct(@RequestParam("name") String name,
							 @RequestParam("category_id") int categoryId ,
							 @RequestParam("price") int price,
							 @RequestParam("discount") int discount,
							 @RequestParam("quantity") int quantity,
							 @RequestParam("description") String description,
							 @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
							 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
							 @RequestParam("productImage") String productImage) {
		System.out.println(categoryId);
		Category category = this.categoryService.getCategory(categoryId);

		Product product = new Product();
		product.setId(categoryId);
		product.setName(name);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(price);
		product.setImage(productImage);
		product.setDiscount(discount);
		product.setQuantity(quantity);
		product.setStartDate(startDate);
		product.setEndDate(endDate);
		this.productService.addProduct(product);

		return "redirect:/admin/products";
	}

	@GetMapping("products/update/{id}")
	public ModelAndView updateProduct(@PathVariable("id") int id) {
		ModelAndView mView = new ModelAndView("productsUpdate");
		Product product = this.productService.getProduct(id);
		List<Category> categories = this.categoryService.getCategories();

		mView.addObject("categories", categories);
		mView.addObject("product", product);
		return mView;
	}

	@RequestMapping(value = "products/update/{id}", method = RequestMethod.POST)
	public String updateProduct(@PathVariable("id") int id,
								@RequestParam("name") String name,
								@RequestParam("category_id") int categoryId ,
								@RequestParam("price") int price,
								@RequestParam("discount") int discount,
								@RequestParam("quantity") int quantity,
								@RequestParam("description") String description,
								@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
								@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
								@RequestParam("productImage") String productImage)
	{
		// Получаем существующий объект Product из базы данных
		Product product = this.productService.getProduct(id);
		if (product == null) {
			// Обработка ситуации, когда продукт с заданным id не найден
			// Можно выбросить исключение или вернуть сообщение об ошибке
			return "redirect:/admin/products";
		}
		// Получаем существующий объект Category из базы данных
		Category category = this.categoryService.getCategory(categoryId);

		// Обновляем поля объекта Product
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setQuantity(quantity);
		product.setDescription(description);
		product.setStartDate(startDate);
		product.setEndDate(endDate);
		product.setImage(productImage);

		// Вызываем метод updateProduct из productService для обновления информации о продукте
		this.productService.updateProduct(product);

		return "redirect:/admin/products";
	}

	@GetMapping("products/delete")
	public String removeProduct(@RequestParam("id") int id)
	{
		this.productService.deleteProduct(id);
		return "redirect:/admin/products";
	}
	
	@PostMapping("products")
	public String postproduct() {
		return "redirect:/admin/categories";
	}
	
	@GetMapping("customers")
	public ModelAndView getCustomerDetail() {
		if(adminlogcheck==0){
			ModelAndView mView = new ModelAndView("adminlogin");
			return mView;
		}
		else {
			ModelAndView mView = new ModelAndView("displayCustomers");
			List<User> users = this.userService.getUsers();
			mView.addObject("customers", users);
			return mView;
		}
	}
	
	
	@GetMapping("profileDisplay")
	public String profileDisplay(Model model) {
		String displayusername,displaypassword,displayemail,displayaddress;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava","root","");
			PreparedStatement stmt = con.prepareStatement("select * from users where username = ?"+";");
			stmt.setString(1, usernameforclass);
			ResultSet rst = stmt.executeQuery();
			
			if(rst.next())
			{
			int userid = rst.getInt(1);
			displayusername = rst.getString(2);
			displayemail = rst.getString(3);
			displaypassword = rst.getString(4);
			// 			displayemail = rst.getString("email");
			// 			displaypassword = rst.getString("password");
			displayaddress = rst.getString(5);
			model.addAttribute("userid",userid);
			model.addAttribute("username",displayusername);
			model.addAttribute("email",displayemail);
			model.addAttribute("password",displaypassword);
			model.addAttribute("address",displayaddress);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception:" + e);
		}
		System.out.println("Hello");
		return "updateProfile";
	}
	
	@RequestMapping(value = "updateuser",method=RequestMethod.POST)
	public String updateUserProfile(@RequestParam("userid") int userid,@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) 
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava","root","");
			
			PreparedStatement pst = con.prepareStatement("update users set username= ?,email = ?,password= ?, address= ? where uid = ?;");
			pst.setString(1, username);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.setString(4, address);
			pst.setInt(5, userid);
			int i = pst.executeUpdate();	
			usernameforclass = username;
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		return "redirect:/index";
	}

}
