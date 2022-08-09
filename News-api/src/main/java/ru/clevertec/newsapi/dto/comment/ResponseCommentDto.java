package ru.clevertec.newsapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * DTO class for representing comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentDto {
    private Long id;
    private LocalDateTime date;
    @NotBlank
    private String text;
    @NotBlank
    private String username;

}
