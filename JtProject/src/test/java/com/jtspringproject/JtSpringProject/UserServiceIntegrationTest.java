package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;
import org.springframework.dao.DataIntegrityViolationException;

import javax.naming.AuthenticationException;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private userService userService;

    @Test
    public void testAddUser_UserExists() {
        // Подготовка данных
        String username = "existingUser";
        String password = "password123";
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Добавление пользователя
        userService.addUser(newUser);

        // Попытка добавить пользователя с уже существующим именем
        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setPassword("otherPassword");

        // Проверка, что при добавлении пользователя с уже существующим именем выбрасывается исключение DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> userService.addUser(existingUser));
    }

    @Test
    public void testCheckLogin_CorrectCredentials() {
        // Подготовка данных
        String username = "testUser1";
        String password = "password123";
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Добавление пользователя
        userService.addUser(newUser);

        // Проверка входа с правильными учетными данными
        User loggedInUser = userService.checkLogin(username, password);
        assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
        assertEquals(password, loggedInUser.getPassword());
    }

}
