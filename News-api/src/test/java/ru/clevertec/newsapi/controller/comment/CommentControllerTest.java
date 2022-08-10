package ru.clevertec.newsapi.controller.comment;

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
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.service.comment.CommentService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentService commentService;

    private ResponseCommentDto firstComment;
    private ResponseCommentDto secondComment;
    private RequestCommentDto newComment;
    private List<ResponseCommentDto> comments;

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

        firstComment = ResponseCommentDto.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Comment #1")
                .username("Alex")
                .build();

       secondComment = ResponseCommentDto.builder()
                .id(2L)
                .date(LocalDateTime.of(2022, 7, 31, 11, 35, 53))
                .text("Comment #2")
                .username("Alex")
                .build();

       comments = List.of(firstComment, secondComment);

    }

    @Test
    @DisplayName("Controller test of creating comment, when request from user is valid")
    void checkResponseFor_CreateComment_IfRequestValid() throws Exception {
        Long id = 1L;

        Mockito.when(commentService.createComment(newComment)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .content(gson.toJson(newComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).createComment(newComment);

    }

    @Test
    @DisplayName("Controller test of updating comment, when request from user is valid")
    void checkResponseFor_UpdateNews_IfRequestValid() throws Exception {
        Long id = 1L;

        Mockito.when(commentService.updateComment(id, newComment)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.put("/comments/{id}", id)
                        .content(gson.toJson(newComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).updateComment(id, newComment);
    }

    @Test
    @DisplayName("Controller test of updating comment, when request from user is not valid")
    void checkResponseFor_UpdateComment_IfRequestIsNotValid() throws Exception {
        Long id = -1L;


        Mockito.when(commentService.updateComment(id, newComment)).thenThrow(new EntityByIdNotFoundException(id));

        mockMvc.perform(MockMvcRequestBuilders.put("/comments/{id}", id)
                        .content(gson.toJson(newComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).updateComment(id, newComment);

    }

    @Test
    @DisplayName("Controller test of deleting comment, when request from user is valid")
    void checkResponseFor_DeleteComment_IfRequestIsValid() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).deleteComment(id);

    }

    @Test
    @DisplayName("Controller test of finding comment by id, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetComment_IfCommentWithSuchIdExistInDB() throws Exception {
        Long id = 1L;

        Mockito.when(commentService.getComment(id)).thenReturn(firstComment);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(firstComment.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).getComment(id);
    }

    @Test
    @DisplayName("Controller test of finding comment by id, when request from user is not valid")
    void checkResponseFor_GetComment_IfCommentWithSuchIdNotExistInDB() throws Exception {
        Long id = 4L;

        Mockito.when(commentService.getComment(id)).thenThrow(new EntityByIdNotFoundException(id));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).getComment(id);

    }

    @Test
    @DisplayName("Controller test of finding all comment for chosen news id, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetCommentsByNewsId_IfSomeCommentsExistInDBForChosenNews() throws Exception {
        Long newsId = 1L;

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(commentService.getCommentsByNewsId(eq(newsId), pageableCaptor.capture())).thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/news/{newsId}", newsId)
                        .param("page", "0")
                        .param("size", "6")
                        .param("sort", "comment_created_at,desc")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(firstComment.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(secondComment.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(commentService, Mockito.times(1)).getCommentsByNewsId(eq(newsId), pageableCaptor.capture());
    }


}
