package ru.clevertec.newsapi.service.news;

import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;

import java.util.List;

public interface NewsService {

    Long createNews(RequestNewsDto requestNewsDto);

    Long updateNews(Long id, RequestNewsDto requestNewsDto);

    ResponseNewsDto getNews(Long id, Pageable pageable);

    void deleteNews(Long id);

    List<ResponseNewsDto> getAllNews(Pageable pageable);

    List<ResponseNewsDto> search(String search, Pageable pageable);



}
