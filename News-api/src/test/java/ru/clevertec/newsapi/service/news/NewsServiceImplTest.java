package ru.clevertec.newsapi.service.news;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.exception.EntityAlreadyExistException;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;
import ru.clevertec.newsapi.mapper.news.NewsMapper;
import ru.clevertec.newsapi.repository.comment.CommentRepository;
import ru.clevertec.newsapi.repository.news.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for news service")
public class NewsServiceImplTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentListMapper commentListMapper;

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private NewsServiceImp newsService;

    private News news;

    private RequestNewsDto requestNewsDto;

    private ResponseNewsDto responseNewsDto;

    private Comment comment;

    private ResponseCommentDto commentDto;


    @BeforeEach
    @Test
    void init() {
        comment = Comment.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Hello!")
                .username("Alex")
                .build();

        commentDto = ResponseCommentDto.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Hello!")
                .username("Alex")
                .build();

        news = News.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 30, 22, 31, 53, 133459))
                .title("New Iphone 14")
                .text("Some text...")
                .comments(List.of(comment))
                .build();

        requestNewsDto = RequestNewsDto.builder()
                .title("New Iphone 14")
                .text("Some text...")
                .build();

        responseNewsDto = ResponseNewsDto.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 30, 22, 31, 53, 133459))
                .title("New Iphone 14")
                .text("Some text...")
                .comments(List.of(commentDto))
                .build();

    }

    @Test
    @DisplayName("News creation test for valid request body")
    void checkResponseFor_CreateNews_WhenRequestBodyIsValid() {
        News validNewsFromDB = News.builder()
                .id(1L)
                .build();
        Mockito.when(newsMapper.toNews(requestNewsDto)).thenReturn(news);
        Mockito.when(newsRepository.save(news)).thenReturn(validNewsFromDB);

        Assertions.assertEquals(validNewsFromDB.getId(), newsService.createNews(requestNewsDto));

        Mockito.verify(newsMapper, Mockito.times(1)).toNews(requestNewsDto);
        Mockito.verify(newsRepository, Mockito.times(1)).save(news);
    }

    @Test
    @DisplayName("News creation test, when title or text already exist ")
    void checkResponseFor_CreateNews_WhenNewsTitleAlreadyExist() {
        News existedNews = news;
        Mockito.when(newsMapper.toNews(requestNewsDto)).thenReturn(news);
        Mockito.when(newsRepository.save(existedNews)).thenThrow(new EntityAlreadyExistException());

        Assertions.assertThrows(EntityAlreadyExistException.class, () -> newsService.createNews(requestNewsDto));

        Mockito.verify(newsMapper, Mockito.times(1)).toNews(requestNewsDto);
        Mockito.verify(newsRepository, Mockito.times(1)).save(news);
    }

    @Test
    @DisplayName("News update test for valid news id and valid news fields")
    void checkResponseFor_UpdateNews_WhenNewsIdExistInDBAndNewsFieldsValuesAreValid() {
        Long id = 1L;

        Mockito.when(newsRepository.findById(id)).thenReturn(Optional.ofNullable(news));

        Assertions.assertEquals(news.getId(), newsService.updateNews(id, requestNewsDto));

        Mockito.verify(newsRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("News update test for invalid news id and valid news fields")
    void checkResponseFor_UpdateNews_WhenNewsIdNotExistInDBAndNewsFieldsValuesAreValid() {
        Long id = 3L;

        Mockito.when(newsRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));

        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> newsService.updateNews(id, requestNewsDto));

        Mockito.verify(newsRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("All news search test when DB has some news ")
    void checkResponseFor_GetAllNews_WhenSomeNewsExistInDBA() {

        Pageable pageable = PageRequest.of(0, 10);
        List<News> newsList = List.of(news);
        Page<News> newsPage = new PageImpl<>(newsList);

        List<ResponseNewsDto> newsDtoList = List.of(responseNewsDto);

        Mockito.when(newsRepository.findAll(pageable)).thenReturn(newsPage);
        Mockito.when(newsMapper.toDto(news)).thenReturn(responseNewsDto);

        Assertions.assertEquals(newsDtoList, newsService.getAllNews(pageable));

        Mockito.verify(newsRepository, Mockito.times(1)).findAll(pageable);
        Mockito.verify(newsMapper, Mockito.times(1)).toDto(news);

    }

    @Test
    @DisplayName("News search test by id when DB has such id ")
    void checkResponseFor_GetNews_WhenNewsWithSuchIdExistInDB() {
        Long id = 1L;

        Pageable pageable = PageRequest.of(0, 10);
        List<Comment> comments = List.of(comment);
        Page<Comment> commentPage = new PageImpl<>(comments);

        List<ResponseCommentDto> responseCommentDtos = List.of(commentDto);

        Mockito.when(newsRepository.findById(id)).thenReturn(Optional.ofNullable(news));
        Mockito.when(newsMapper.toDto(news)).thenReturn(responseNewsDto);
        Mockito.when(commentRepository.findAllByNews_Id(id, pageable)).thenReturn(commentPage);
        Mockito.when(commentListMapper.toDtoList(commentPage.getContent())).thenReturn(responseCommentDtos);

        Assertions.assertEquals(responseNewsDto, newsService.getNews(id, pageable));

        Mockito.verify(newsRepository, Mockito.times(1)).findById(id);
        Mockito.verify(newsMapper, Mockito.times(1)).toDto(news);
        Mockito.verify(commentRepository, Mockito.times(1)).findAllByNews_Id(id, pageable);
        Mockito.verify(commentListMapper, Mockito.times(1)).toDtoList(commentPage.getContent());

    }

    @Test
    @DisplayName("News search test by id when DB hasn`t such id ")
    void checkResponseFor_GetNews_WhenNewsWithSuchIdNotExistInDB() {
        Long id = 3L;
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(newsRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));

        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> newsService.getNews(id, pageable));

        Mockito.verify(newsRepository, Mockito.times(1)).findById(id);
        Mockito.verify(newsMapper, Mockito.times(0)).toDto(news);
        Mockito.verify(commentRepository, Mockito.times(0)).findAllByNews_Id(id, pageable);

    }

    @Test
    @DisplayName("News search test by keyword among title and text when DB has such word ")
    void checkResponseFor_Search_WhenNewsWithSuchKeywordExistInDBAmongTitleOrText() {

        String keyword = "Iphone";
        Mockito.when(newsRepository.search(keyword)).thenReturn(List.of(news));
        Mockito.when(newsMapper.toDto(news)).thenReturn(responseNewsDto);

        Assertions.assertEquals(List.of(responseNewsDto), newsService.search(keyword));

        Mockito.verify(newsRepository, Mockito.times(1)).search(keyword);
        Mockito.verify(newsMapper, Mockito.times(1)).toDto(news);

    }

    @Test
    @DisplayName("News delete test by id")
    void checkResponseFor_DeleteNews_WhenNewsWithSuchIdExistInDB() {

        Long id = 1L;
        newsService.deleteNews(id);
        Mockito.verify(newsRepository, Mockito.times(1)).deleteById(id);

    }

}
