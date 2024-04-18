package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.productService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Test
    public void testAddProduct() {
        // Создание фиктивного объекта продукта
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

        // Создание мок-объекта productService
        productService productServiceMock = Mockito.mock(productService.class);

        // Установка поведения мок-объекта
        when(productServiceMock.addProduct(product)).thenReturn(product);

        // Вызов метода для тестирования
        Product addedProduct = productServiceMock.addProduct(product);

        // Проверка успешного добавления продукта
        assertNotNull(addedProduct);
        assertEquals("Apple", addedProduct.getName());
        assertEquals(3, addedProduct.getPrice());
        assertEquals("", addedProduct.getImage());
        assertEquals(2, addedProduct.getDiscount());
        assertEquals("test", addedProduct.getDescription());
        assertEquals(testCategory.getId(), addedProduct.getCategory().getId());
        assertEquals(LocalDate.parse("2024-01-01"), addedProduct.getEndDate());
        assertEquals(LocalDate.parse("2024-01-01"), addedProduct.getStartDate());
    }
}
