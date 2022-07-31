package ru.clevertec.newsapi.mapper.news;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.news.NewsDto;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;

@Mapper(componentModel = "spring", uses = CommentListMapper.class)
public interface NewsMapper {
    NewsDto toDto(News news);
    News toNews(NewsDto dto);
}
