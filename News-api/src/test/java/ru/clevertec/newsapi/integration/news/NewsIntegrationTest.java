package ru.clevertec.newsapi.integration.news;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NewsIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    private RequestNewsDto newNews;
    private RequestNewsDto updateNews;

    @BeforeEach
    @Test
    void init() {
        newNews = RequestNewsDto.builder()
                .title("News #21")
                .text("Text #21")
                .build();

        updateNews = RequestNewsDto.builder()
                .title("News #10")
                .text("Text #10")
                .build();
    }

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.2")
            .withDatabaseName("news_test_db")
            .withUsername("admin")
            .withPassword("12345")
            .withInitScript("scripts/data.sql");


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @DisplayName("Integration test of creating news, when request from user is valid")
    void createNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/news")
                        .content(gson.toJson(newNews))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of getting all news, when request from user is valid")
    void getNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/news")
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "id,asc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Integration test of getting all news by keyword, when request from user is valid")
    void getAllNewsByKeyword() throws Exception {
        String keyword = "13";
        mockMvc.perform(MockMvcRequestBuilders.get("/news/search")
                        .param("keyword", keyword)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(13)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of updating news, when request from user is valid")
    void updateNews() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.put("/news/{id}", id)
                        .content(gson.toJson(updateNews))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of deleting news, when request from user is valid")
    void deleteNews() throws Exception {
        Long id = 12L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/news/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}




