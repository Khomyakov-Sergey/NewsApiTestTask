package ru.clevertec.newsapi.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.dto.comment.CreateCommentDto;
import ru.clevertec.newsapi.service.comment.CommentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for comments. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RequestMapping("/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createComment(@RequestBody @Valid CreateCommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateComment(@PathVariable Long id, @RequestBody @Valid CommentDto commentDto) {
        return commentService.updateComment(id, commentDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @GetMapping("/news/{newsId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments(@PathVariable Long newsId, Pageable pageable) {
        return commentService.getCommentsByNewsId(newsId, pageable);
    }
}
