package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryDaoTest {

    @Autowired
    private categoryDao categoryDAO;

    // Проверка добавления новой категории
    @Test
    public void testAddCategory() {
        // Создаем новую категорию
        String categoryName = "CategoryTest";
        Category newCategory = new Category();
        newCategory.setName(categoryName);

        // Добавляем категорию
        Category addedCategory = categoryDAO.addCategory(newCategory.getName());

        // Проверяем, что категория была успешно добавлена
        assertNotNull(addedCategory);
        assertNotNull(addedCategory.getId()); // Проверяем, что у категории присвоен ID после добавления
        assertEquals(categoryName, addedCategory.getName());

        System.out.println("Проверка добавления новой категории + ");
    }
}
