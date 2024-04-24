package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtspringproject.JtSpringProject.models.Category;

@SpringBootTest
public class CreateCategoryModelTest {

    @Test
    public void testCategoryModelCreation() {
        // Создаем экземпляр класса Category
        Category category = new Category(123, "Test Category");

        // Проверяем, что экземпляр был создан
        assertNotNull(category);

        // Проверяем, что поля установлены правильно
        assertEquals(123, category.getId());
        assertEquals("Test Category", category.getName());
    }
}
