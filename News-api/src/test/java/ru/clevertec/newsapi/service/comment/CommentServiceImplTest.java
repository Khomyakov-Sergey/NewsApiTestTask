package ru.clevertec.newsapi.service.comment;

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
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.entity.news.News;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;
import ru.clevertec.newsapi.mapper.comment.CommentMapper;
import ru.clevertec.newsapi.repository.comment.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for comment service")
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private CommentListMapper commentListMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;

    private RequestCommentDto requestCommentDto;

    private ResponseCommentDto responseCommentDto;

    @BeforeEach
    @Test
    void init() {

        News news = News.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 30, 22, 31, 53, 133459))
                .title("New Iphone 14")
                .text("Some text...")
                .build();

        ResponseNewsDto newsDto = ResponseNewsDto.builder()
                .id(1L)
                .build();

        comment = Comment.builder()
                .id(2L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Good comment for test...")
                .username("Smith")
                .news(news)
                .build();

        responseCommentDto = ResponseCommentDto.builder()
                .id(1L)
                .date(LocalDateTime.of(2022, 7, 31, 10, 35, 53))
                .text("Good comment for test...")
                .username("Alex")
                .build();

        requestCommentDto = RequestCommentDto.builder()
                .text("Good comment for test...")
                .username("Smith")
                .newsDto(newsDto)
                .build();

    }

    @Test
    @DisplayName("Comment creation test for valid request body")
    void checkResponseFor_CreateNewsComment_WhenRequestBodyIsValid() {
        Comment validCommentFromDB = Comment.builder()
                .id(2L)
                .build();

        Mockito.when(commentMapper.toComment(requestCommentDto)).thenReturn(comment);
        Mockito.when(commentRepository.save(comment)).thenReturn(validCommentFromDB);

        Assertions.assertEquals(validCommentFromDB.getId(), commentService.createComment(requestCommentDto));

        Mockito.verify(commentMapper, Mockito.times(1)).toComment(requestCommentDto);
        Mockito.verify(commentRepository, Mockito.times(1)).save(comment);
    }

    @Test
    @DisplayName("Comment update test for valid comment id and valid comment fields")
    void checkResponseFor_UpdateComment_WhenCommentIdExistInDBAndCommentFieldsValuesAreValid() {
        Long id = 2L;

        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.ofNullable(comment));

        Assertions.assertEquals(comment.getId(), commentService.updateComment(id, requestCommentDto));

        Mockito.verify(commentRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("Comment update test for invalid comment id and valid comment fields")
    void checkResponseFor_UpdateNews_WhenNewsIdExistInDBAndNewsFieldsValuesAreValid() {
        Long id = 2L;

        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.ofNullable(comment));

        Assertions.assertEquals(comment.getId(), commentService.updateComment(id, requestCommentDto));

        Mockito.verify(commentRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("Comment delete test by id")
    void checkResponseFor_DeleteComment_WhenCommentWithSuchIdExistInDB() {

        Long id = 2L;
        commentService.deleteComment(id);
        Mockito.verify(commentRepository, Mockito.times(1)).deleteById(id);

    }

    @Test
    @DisplayName("Comment search test by id when DB has such id ")
    void checkResponseFor_GetComment_WhenCommentWithSuchIdExistInDB() {
        Long id = 2L;

        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.ofNullable(comment));
        Mockito.when(commentMapper.toDto(comment)).thenReturn(responseCommentDto);

        Assertions.assertEquals(responseCommentDto, commentService.getComment(id));

        Mockito.verify(commentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(commentMapper, Mockito.times(1)).toDto(comment);

    }

    @Test
    @DisplayName("Comment search test by id when id not exist in DB")
    void checkResponseFor_GetComment_WhenCommentWithSuchIdNotExistInDB() {
        Long id = 3L;

        Mockito.when(commentRepository.findById(id)).thenThrow(new EntityByIdNotFoundException(id));

        Assertions.assertThrows(EntityByIdNotFoundException.class, () -> commentService.getComment(id));

        Mockito.verify(commentRepository, Mockito.times(1)).findById(id);
        Mockito.verify(commentMapper, Mockito.times(0)).toDto(comment);

    }

    @Test
    @DisplayName("Comments search test by news id when DB has such id")
    void checkResponseFor_GetCommentsByNewsId_WhenNewsWithSuchIdExistInDB() {
        Long id = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        List<Comment> comments = List.of(comment);
        Page<Comment> commentPage = new PageImpl<>(comments);

        List<ResponseCommentDto> commentDtoList = List.of(responseCommentDto);

        Mockito.when(commentRepository.findAllByNews_Id(id, pageable)).thenReturn(commentPage);
        Mockito.when(commentListMapper.toDtoList(commentPage.getContent())).thenReturn(commentDtoList);

        Assertions.assertEquals(commentDtoList, commentService.getCommentsByNewsId(id, pageable));

        Mockito.verify(commentRepository, Mockito.times(1)).findAllByNews_Id(id, pageable);
        Mockito.verify(commentListMapper, Mockito.times(1)).toDtoList(commentPage.getContent());

    }


}
