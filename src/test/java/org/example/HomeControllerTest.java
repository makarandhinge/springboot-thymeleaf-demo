package org.example;

import org.example.controller.HomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("1️⃣ Home page returns HTTP 200")
    void testHomePageReturnsOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("2️⃣ Home page renders 'index' view")
    void testHomePageRendersIndexView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("3️⃣ Model contains title and message")
    void testModelAttributesExist() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    @DisplayName("4️⃣ Title matches expected text")
    void testTitleIsCorrect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attribute("title", "Welcome to My Spring Boot + Thymeleaf Website!"));
    }

    @Test
    @DisplayName("5️⃣ HTML response contains welcome message")
    void testResponseBodyContainsWelcomeText() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to My Spring Boot + Thymeleaf Website!")));
    }
}
