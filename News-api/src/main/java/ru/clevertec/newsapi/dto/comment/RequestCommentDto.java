package ru.clevertec.newsapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO class for creating comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentDto {
    @NotBlank
    private String text;
    @NotBlank
    private String username;
    @NotNull
    private ResponseNewsDto newsDto;

}
