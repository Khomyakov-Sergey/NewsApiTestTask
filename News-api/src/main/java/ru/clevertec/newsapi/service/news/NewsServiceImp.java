package ru.clevertec.newsapi.service.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.newsapi.dto.news.NewsDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;
import ru.clevertec.newsapi.mapper.news.NewsMapper;
import ru.clevertec.newsapi.repository.comment.CommentRepository;
import ru.clevertec.newsapi.repository.news.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service class for news with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;

    private final CommentRepository commentRepository;

    private final CommentListMapper commentListMapper;

    private final NewsMapper newsMapper;

    /**
     * This method searches all news by using NewsRepository.
     * @return List<NewsDto> - List of all News representation in DTO.
     */
    @Override
    public List<NewsDto> getAllNews(Pageable pageable) {
        List<News> news = newsRepository.findAll(pageable).getContent();
        return news.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * This method creates new news and transfers it in NewsRepository to save.
     * @param newsDto - News information from request.
     * @return id - News identifier.
     */
    @Override
    @Transactional
    public Long createNews(NewsDto newsDto) {
        News news = newsMapper.toNews(newsDto);
        return newsRepository.save(news).getId();
    }

    /**
     * This method searches news by the transferred id and updates information by using NewsRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException.
     * @param id - News identifier.
     * @param newsDto - News information from request.
     * @return id - News identifier.
     */
    @Override
    @Transactional
    public Long updateNews(Long id, NewsDto newsDto) {
        News news = newsRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        return news.getId();
    }

    /**
     * This method searches news by the transferred id using NewsRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException. For list of comments in news supporting different
     * sorting by using CommentRepository and pagination.
     * @param id - Product identifier.
     * @param pageable - Pagination for list of comments.
     * @return NewsDto - News representation in DTO.
     */
    @Override
    public NewsDto getNews(Long id, Pageable pageable) {
        News news = newsRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        NewsDto newsDto = newsMapper.toDto(news);
        List<Comment> comments = commentRepository.findAllByNews_Id(id, pageable).getContent();
        newsDto.setComments(commentListMapper.toDtoList(comments));
        return newsDto;
    }

    /**
     * This method searches news by the transferred keyword among fields text and title using NewsRepository.
     * @param keyword - Keyword for searching.
     * @return List<NewsDto> - List of news representation in DTO.
     */
    @Override
    public List<NewsDto> search(String keyword) {
        List<News> news = newsRepository.search(keyword);
        return news.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * This method searches news by the transferred id. If it exists, deletes it by using NewsRepository.
     * @param id - News identifier.
     */
    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
