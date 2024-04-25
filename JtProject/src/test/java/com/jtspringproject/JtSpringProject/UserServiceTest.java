package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private userService userService;

    @Test
    public void testGetUsers_ReturnsListOfUsers() {
        // Подготовка данных
        List<User> users = userService.getUsers();

        // Проверка
        assertEquals(2, users.size());
    }
}
