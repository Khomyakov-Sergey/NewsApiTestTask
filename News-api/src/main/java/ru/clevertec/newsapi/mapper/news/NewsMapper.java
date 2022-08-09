package ru.clevertec.newsapi.mapper.news;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;

/**
 * Mapper class for news. It for converts News to NewsDto and vice versa.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Mapper(componentModel = "spring", uses = CommentListMapper.class)
public interface NewsMapper {

    /**
     * This method converts News to NewsDto.
     * @param news - Entity News.
     * @return NewsDto - News representation in DTO.
     */
    ResponseNewsDto toDto(News news);

    /**
     * This method converts News to NewsDto.
     * @param newsDto - News representation in DTO.
     * @return news - Entity News.
     */
    News toNews(RequestNewsDto newsDto);
}
