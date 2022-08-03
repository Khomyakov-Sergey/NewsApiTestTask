package ru.clevertec.newsapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.newsapi.dto.news.NewsDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO class for creating comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentDto {
    private Long id;
    private LocalDateTime date;
    @NotBlank
    private String text;
    @NotBlank
    private String username;
    @NotNull
    private NewsDto newsDto;

}
