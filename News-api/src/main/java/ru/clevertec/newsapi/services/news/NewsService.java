package ru.clevertec.newsapi.services.news;

import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.NewsDto;

import java.util.List;

public interface NewsService {

    Long createNews(NewsDto requestNewsDto);

    Long updateNews(Long id, NewsDto requestNewsDto);

    NewsDto getNews(Long id);

    void deleteNews(Long id);

    List<NewsDto> getAllNews(Pageable pageable);

    List<NewsDto> search(String search);



}
