package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.categoryService;
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

    @Autowired
    private categoryService categoryService;

    @Test
    public void testUpdateProduct() {
        // Создание фиктивного продукта
        Product product = new Product();
        product.setName("Дыня");
        product.setPrice(3);
        product.setQuantity(40);
        product.setDiscount(2);
        product.setImage("");
        product.setDescription("test");

        // Получение существующей категории из базы данных
        Category testCategory = categoryService.getCategory(1);
        product.setCategory(testCategory);
        product.setEndDate(LocalDate.parse("2024-07-01"));
        product.setStartDate(LocalDate.parse("2024-08-30"));

        // Добавление продукта в базу данных
        Product addedProduct = productService.addProduct(product);

        // Обновление продукта через контроллер
        String updatedProductName = "Новое яблоко";
        adminController.updateProduct(
                addedProduct.getId(),
                updatedProductName,
                testCategory.getId(),
                4,
                3,
                50,
                "тест",
                LocalDate.parse("2024-07-01"),
                LocalDate.parse("2024-09-30"),
                "");

        // Получение обновленного продукта
        Product updatedProduct = productService.getProduct(addedProduct.getId());

        // Проверка успешного обновления имени продукта
        assertEquals(updatedProductName, updatedProduct.getName());

        System.out.println("Проверка обновления продукта + ");
    }

    @Test
    public void testDeleteProduct() {
        // Создание фиктивного продукта
        Product product = new Product();
        product.setName("Тестовый продукт");
        product.setPrice(10);
        product.setQuantity(20);
        product.setDiscount(0);
        product.setImage("");
        product.setDescription("Тестовое описание");

        // Получение существующей категории из базы данных
        Category testCategory = categoryService.getCategory(9);
        product.setCategory(testCategory);
        product.setEndDate(LocalDate.parse("2024-07-01"));
        product.setStartDate(LocalDate.parse("2024-08-30"));

        // Добавление продукта в базу данных
        Product addedProduct = productService.addProduct(product);

        // Попытка удаления продукта через контроллер
        assertDoesNotThrow(() -> adminController.removeProduct(addedProduct.getId()));

        System.out.println("Проверка удаления продукта + ");
    }
}
