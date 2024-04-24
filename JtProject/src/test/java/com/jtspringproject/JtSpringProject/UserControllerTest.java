package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private userService userService;

    @Mock
    private productService productService;

    // @InjectMocks поможет автоматически внедрить моки в контроллер
    @InjectMocks
    private UserController userController;

    @Test
    public void testUserLogin() throws Exception {
        // Создаем пользователя для входа
        User testUser = new User();
        testUser.setUsername("lisa");
        testUser.setPassword("765");

        // Когда вызывается userService.checkLogin() с указанными логином и паролем, возвращаем тестового пользователя
        when(userService.checkLogin("lisa", "765")).thenReturn(testUser);

        // Отправляем POST запрос на эндпоинт для входа
        MvcResult result = mockMvc.perform(post("/userloginvalidate")
                        .param("username", "lisa")
                        .param("password", "765")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("user"))
                .andReturn();

        // Проверяем, что пользователь успешно вошел
        User loggedInUser = (User) result.getModelAndView().getModel().get("user");
        assert loggedInUser != null;
        assert loggedInUser.getUsername().equals("lisa");
    }
}
