package ru.clevertec.newsapi.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
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

    /**
     * This method gets CreateCommentDto and tries to create comment for news with using service layer.
     * @param requestCommentDto - Comment information from request.
     * @return id - Comment identifier.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createComment(@RequestBody @Valid RequestCommentDto requestCommentDto) {
        return commentService.createComment(requestCommentDto);
    }

    /**
     * This method gets comment identifier, CommentDto and tries to update comment with using service layer.
     * @param id  - Comment identifier.
     * @param requestCommentDto - Comment information from request.
     * @return id - Comment identifier.
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateComment(@PathVariable Long id, @RequestBody @Valid RequestCommentDto requestCommentDto) {
        return commentService.updateComment(id, requestCommentDto);
    }

    /**
     * This method gets comment identifier and tries to delete it with using service layer.
     * @param id - Comment identifier.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    /**
     * This method gets comment identifier and tries to find comment with using service layer.
     * @param id - Comment identifier.
     * @return CommentDto - Comment representation.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCommentDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    /**
     * This method gets all comments for definite news id with using service layer. Also supporting pageable view.
     * @return List<CommentDto> - List of comments.
     */
    @GetMapping("/news/{newsId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCommentDto> getComments(@PathVariable Long newsId, Pageable pageable) {
        return commentService.getCommentsByNewsId(newsId, pageable);
    }
}
