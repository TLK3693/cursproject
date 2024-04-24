package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.dao.productDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private productService productService;

    @Autowired
    private productDao productDao;

    @Autowired
    private com.jtspringproject.JtSpringProject.services.categoryService categoryService;

    @Test
    @Transactional
    public void testAddProduct() {
        // Создание фиктивного объекта продукта
        Product product = new Product();
        product.setName("Яблоко");
        product.setPrice(3);
        product.setQuantity(40);
        product.setDiscount(2);
        product.setImage("");
        product.setDescription("тест");
        Category testCategory = categoryService.getCategory(1);
        product.setCategory(testCategory);
        product.setEndDate(LocalDate.parse("2024-01-01"));
        product.setStartDate(LocalDate.parse("2024-01-01"));

        // Вызов метода для тестирования
        Product addedProduct = productService.addProduct(product);

        // Проверка успешного добавления продукта
        assertNotNull(addedProduct);
        assertEquals("Яблоко", addedProduct.getName());
        assertEquals(3, addedProduct.getPrice());
        assertEquals("", addedProduct.getImage());
        assertEquals(2, addedProduct.getDiscount());
        assertEquals("тест", addedProduct.getDescription());
        assertEquals(40, addedProduct.getQuantity());
        assertEquals(testCategory.getId(), addedProduct.getCategory().getId());
        assertEquals(LocalDate.parse("2024-01-01"), addedProduct.getEndDate());
        assertEquals(LocalDate.parse("2024-01-01"), addedProduct.getStartDate());
    }
}
