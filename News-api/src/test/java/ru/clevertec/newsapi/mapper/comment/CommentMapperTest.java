package ru.clevertec.newsapi.mapper.comment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;

import java.time.LocalDateTime;

@SpringBootTest(classes = {CommentMapperImpl.class})
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
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
    void checkRequestCommentDtoToCommentEntityWhenAllValueValid() {
        RequestCommentDto commentDto = RequestCommentDto.builder()
                .text("Text")
                .username("Alex")
                .newsDto(ResponseNewsDto.builder()
                        .id(1L)
                        .build())
                .build();

        Comment comment = commentMapper.toComment(commentDto);
        Assertions.assertEquals(comment.getText(), commentDto.getText());
        Assertions.assertEquals(comment.getUsername(), commentDto.getUsername());
        Assertions.assertEquals(comment.getNews().getId(), commentDto.getNewsDto().getId());
    }
}
