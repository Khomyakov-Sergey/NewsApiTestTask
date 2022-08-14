package ru.clevertec.newsapi.controller.news;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.exception.EntityAlreadyExistException;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.service.news.NewsService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private NewsService newsService;

    private RequestNewsDto newNews;
    private ResponseNewsDto firstNews;
    private ResponseNewsDto secondNews;
    private List<ResponseNewsDto> newsList;


    @BeforeEach
    @Test
    void init() {
        firstNews = ResponseNewsDto.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .title("News #1")
                .text("Text #1")
                .build();

        secondNews = ResponseNewsDto.builder()
                .id(2L)
                .date(LocalDateTime.of(2022, 7, 31, 11, 35, 53))
                .title("News #2")
                .text("Text #2")
                .build();

        newsList = List.of(firstNews, secondNews);

        newNews = RequestNewsDto.builder()
                .title("News #3")
                .text("Text #3")
                .build();

    }

    @Test
    @DisplayName("Controller test of finding all news, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetAllNews_IfSomeNewsExistInDB() throws Exception {

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(newsService.getAllNews(pageableCaptor.capture())).thenReturn(newsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/news/")
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "news_created_at,desc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(firstNews.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(secondNews.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).getAllNews(pageableCaptor.capture());
    }

    @Test
    @DisplayName("Controller test of finding news by id, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetNews_IfNewsWithSuchIdExistInDB() throws Exception {
        Long id = 1L;

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(newsService.getNews(eq(id), pageableCaptor.capture())).thenReturn(firstNews);

        mockMvc.perform(MockMvcRequestBuilders.get("/news/{id}", id)
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "news_created_at,desc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(firstNews.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).getNews(eq(id), pageableCaptor.capture());
    }

    @Test
    @DisplayName("Controller test of finding news by id, when request from user is not valid")
    void checkResponseFor_GetNews_IfNewsWithSuchIdNotExistInDB() throws Exception {
        Long id = 4L;

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(newsService.getNews(eq(id), pageableCaptor.capture())).thenThrow(new EntityByIdNotFoundException(id));

        mockMvc.perform(MockMvcRequestBuilders.get("/news/{id}", id)
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "news_created_at,desc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).getNews(eq(id), pageableCaptor.capture());

    }

    @Test
    @DisplayName("Controller test of creating news, when request from user is valid")
    void checkResponseFor_CreateNews_IfRequestValid() throws Exception {
        Long id = 3L;

        Mockito.when(newsService.createNews(newNews)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/news")
                        .content(gson.toJson(newNews))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).createNews(newNews);

    }

    @Test
    @DisplayName("Controller test of creating news, when request from user is not valid")
    void checkResponseFor_CreateNews_IfRequestIsNotValid() throws Exception {

        Mockito.when(newsService.createNews(newNews)).thenThrow(new EntityAlreadyExistException());

        mockMvc.perform(MockMvcRequestBuilders.post("/news")
                        .content(gson.toJson(newNews))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityAlreadyExistException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).createNews(newNews);

    }

    @Test
    @DisplayName("Controller test of updating news, when request from user is valid")
    void checkResponseFor_UpdateNews_IfRequestValid() throws Exception {
        Long id = 1L;

        Mockito.when(newsService.updateNews(id, newNews)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.put("/news/{id}", id)
                        .content(gson.toJson(newNews))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).updateNews(id, newNews);
    }

    @Test
    @DisplayName("Controller test of updating news, when request from user is not valid")
    void checkResponseFor_UpdateNews_IfRequestIsNotValid() throws Exception {
        Long id = -1L;


        Mockito.when(newsService.updateNews(id, newNews)).thenThrow(new EntityByIdNotFoundException(id));

        mockMvc.perform(MockMvcRequestBuilders.put("/news/{id}", id)
                        .content(gson.toJson(newNews))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).updateNews(id, newNews);

    }

    @Test
    @DisplayName("Controller test of deleting news, when request from user is valid")
    void checkResponseFor_DeleteNews_IfRequestIsValid() throws Exception {
        Long id = 2L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/news/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).deleteNews(id);

    }

    @Test
    @DisplayName("Controller test of searching news among two columns title and text, when request from user is valid")
    void checkResponseFor_SearchNews_IfRequestIsValid() throws Exception {
        String keyword = "Text";

        List<ResponseNewsDto> resultList = List.of(firstNews);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(newsService.search(eq(keyword), pageableCaptor.capture())).thenReturn(resultList);

        mockMvc.perform(MockMvcRequestBuilders.get("/news/search")
                        .param("keyword", keyword)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(firstNews.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(newsService, Mockito.times(1)).search(eq(keyword), pageableCaptor.capture());

    }


}
