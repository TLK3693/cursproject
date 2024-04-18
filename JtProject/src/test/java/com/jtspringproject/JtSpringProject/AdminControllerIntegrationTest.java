package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminControllerIntegrationTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private productService productService;

    @Test
    public void testUpdateProduct() {
        // Создание фиктивного продукта
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(3);
        product.setQuantity(40);
        product.setDiscount(2);
        product.setImage("");
        product.setDescription("test");
        Category testCategory = new Category(123, "name");
        product.setCategory(testCategory);
        product.setEndDate(LocalDate.parse("2024-01-01"));
        product.setStartDate(LocalDate.parse("2024-01-01"));
        Product addedProduct = productService.addProduct(product);

        // Обновление продукта через контроллер
        String updatedProductName = "Green Apple";
        adminController.updateProduct(addedProduct.getId(),
                updatedProductName,testCategory.getId(), 3, 2, 40, "test",
                LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-01"),"");

        // Получение обновленного продукта
        Product updatedProduct = productService.getProduct(addedProduct.getId());

        // Проверка успешного обновления имени продукта
        assertEquals(updatedProductName, updatedProduct.getName());
    }
}
