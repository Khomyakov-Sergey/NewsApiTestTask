package ru.clevertec.newsapi.service.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.newsapi.dto.news.NewsDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;
import ru.clevertec.newsapi.mapper.news.NewsMapper;
import ru.clevertec.newsapi.repository.comment.CommentRepository;
import ru.clevertec.newsapi.repository.news.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;

    private final CommentRepository commentRepository;

    private final CommentListMapper commentListMapper;

    private final NewsMapper newsMapper;

    @Override
    public List<NewsDto> getAllNews(Pageable pageable) {
        List<News> news = newsRepository.findAll(pageable).getContent();
        return news.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createNews(NewsDto newsDto) {
        News news = newsMapper.toNews(newsDto);
        return newsRepository.save(news).getId();
    }

    @Override
    @Transactional
    public Long updateNews(Long id, NewsDto newsDto) {
        News news = newsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setDate(LocalDateTime.now());
        return news.getId();
    }

    @Override
    public NewsDto getNews(Long id, Pageable pageable) {
        News news = newsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        NewsDto newsDto = newsMapper.toDto(news);
        List<Comment> comments = commentRepository.findAllByNews_Id(id, pageable).getContent();
        newsDto.setComments(commentListMapper.toDtoList(comments));
        return newsDto;
    }

    @Override
    public List<NewsDto> search(String search) {
        List<News> news = newsRepository.search(search);
        return news.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
