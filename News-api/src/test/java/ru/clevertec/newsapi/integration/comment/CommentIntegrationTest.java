package ru.clevertec.newsapi.integration.comment;

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
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CommentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    private RequestCommentDto newComment;
    private RequestCommentDto updateComment;

    @BeforeEach
    @Test
    void init() {
        newComment = RequestCommentDto.builder()
                .text("Comment #1")
                .username("Alex")
                .newsDto(ResponseNewsDto.builder()
                        .id(1L)
                        .build())
                .build();

        updateComment = RequestCommentDto.builder()
                .text("Test comment for tests")
                .username("Alex")
                .newsDto(ResponseNewsDto.builder()
                        .id(1L)
                        .build())
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
    @DisplayName("Integration test of creating comment, when request from user is valid")
    void createComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .content(gson.toJson(newComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName("Integration test of updating comment, when request from user is valid")
    void updateComment() throws Exception {
        Long id = 5L;

        mockMvc.perform(MockMvcRequestBuilders.put("/comments/{id}", id)
                        .content(gson.toJson(updateComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of deleting comment, when request from user is valid")
    void deleteComment() throws Exception {
        Long id = 15L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of finding comment by id, when request from user is valid")
    void getComment() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test of searching all comments for chosen news id, when request from user is valid")
    void getCommentsByNewsId() throws Exception {
        Long newsId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/news/{newsId}", newsId)
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "date,asc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andDo(MockMvcResultHandlers.print());

    }

}
