package ru.clevertec.newsapi.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.NewsDto;
import ru.clevertec.newsapi.entity.News;

@Mapper(componentModel = "spring", uses = CommentListMapper.class)
public interface NewsMapper {
    NewsDto toDto(News news);
    News toNews(NewsDto dto);
}
