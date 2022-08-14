package ru.clevertec.newsapi.mapper.comment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;

import java.time.LocalDateTime;

@SpringBootTest(classes = {CommentMapperImpl.class})
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    @DisplayName("Mapper test for converting comment into dto")
    void checkCommentEntityToResponseCommentDtoWhenAllValueValid() {
        Comment comment = Comment.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Text")
                .username("Alex")
                .news(News.builder()
                        .id(1L)
                        .build())
                .build();

        ResponseCommentDto commentDto = commentMapper.toDto(comment);
        Assertions.assertEquals(commentDto.getId(), comment.getId());
        Assertions.assertEquals(commentDto.getDate(), comment.getDate());
        Assertions.assertEquals(commentDto.getUsername(), comment.getUsername());
        Assertions.assertEquals(commentDto.getText(), comment.getText());
    }

    @Test
    @DisplayName("Mapper test for converting dto into comment")
    void checkRequestCommentDtoToCommentEntityWhenAllValueValid() {
        RequestCommentDto commentDto = RequestCommentDto.builder()
                .text("Text")
                .username("Alex")
                .newsId(1L)
                .build();

        Comment comment = commentMapper.toComment(commentDto);
        Assertions.assertEquals(comment.getText(), commentDto.getText());
        Assertions.assertEquals(comment.getUsername(), commentDto.getUsername());
        Assertions.assertEquals(comment.getNews().getId(), commentDto.getNewsId());
    }
}
