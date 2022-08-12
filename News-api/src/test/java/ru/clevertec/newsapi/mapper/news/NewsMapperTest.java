package ru.clevertec.newsapi.mapper.news;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.mapper.comment.CommentListMapperImpl;
import ru.clevertec.newsapi.mapper.comment.CommentMapperImpl;

import java.time.LocalDateTime;

@SpringBootTest(classes = {NewsMapperImpl.class, CommentListMapperImpl.class, CommentMapperImpl.class})
public class NewsMapperTest {
    @Autowired
    private NewsMapper newsMapper;

    @Test
    @DisplayName("Mapper test for converting news into dto")
    void checkNewsEntityToResponseNewsDtoWhenAllValueValid() {
        News news = News.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .title("Title")
                .text("Text")
                .build();

        ResponseNewsDto newsDto = newsMapper.toDto(news);
        Assertions.assertEquals(newsDto.getId(), news.getId());
        Assertions.assertEquals(newsDto.getDate(), news.getDate());
        Assertions.assertEquals(newsDto.getTitle(), news.getTitle());
        Assertions.assertEquals(newsDto.getText(), news.getText());
    }

    @Test
    @DisplayName("Mapper test for converting dto into news")
    void checkRequestNewsDtoToNewsEntityWhenAllValueValid() {
        RequestNewsDto newsDto = RequestNewsDto.builder()
                .title("Title")
                .text("Text")
                .build();

        News news = newsMapper.toNews(newsDto);
        Assertions.assertEquals(newsDto.getTitle(), news.getTitle());
        Assertions.assertEquals(newsDto.getText(), news.getText());
    }
}
