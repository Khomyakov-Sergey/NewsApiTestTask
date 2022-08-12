package ru.clevertec.newsapi.mapper.comment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = {CommentMapperImpl.class, CommentListMapperImpl.class})
public class CommentListMapperTest {

    @Autowired
    private CommentListMapper commentListMapper;

    @Test
    @DisplayName("Mapper test for converting list of comments into list of dto comments")
    void checkCommentsListToResponseCommentDtoListWhenAllValueValid() {
        int index = 0;
        Comment comment = Comment.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Text")
                .username("Alex")
                .news(News.builder()
                        .id(1L)
                        .build())
                .build();
        List<Comment> comments = List.of(comment);

        List<ResponseCommentDto> commentDtos = commentListMapper.toDtoList(comments);
        Assertions.assertEquals(commentDtos.get(index).getId(), comments.get(index).getId());
        Assertions.assertEquals(commentDtos.get(index).getDate(), comments.get(index).getDate());
        Assertions.assertEquals(commentDtos.get(index).getUsername(), comments.get(index).getUsername());
        Assertions.assertEquals(commentDtos.get(index).getText(), comments.get(index).getText());
    }

    @Test
    @DisplayName("Mapper test for converting list of dto comments into list of comments")
    void checkRequestCommentDtoListToCommentEntityListWhenAllValueValid() {
        int index = 0;
        ResponseCommentDto commentDto = ResponseCommentDto.builder()
                .id(1L)
                .text("Text")
                .username("Alex")
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .build();

        List<ResponseCommentDto> commentDtos = List.of(commentDto);

        List<Comment> comments = commentListMapper.toCommentList(commentDtos);
        Assertions.assertEquals(comments.get(index).getId(), commentDtos.get(index).getId());
        Assertions.assertEquals(comments.get(index).getDate(), commentDtos.get(index).getDate());
        Assertions.assertEquals(comments.get(index).getText(), commentDtos.get(index).getText());
        Assertions.assertEquals(comments.get(index).getUsername(), commentDtos.get(index).getUsername());

    }
}
