package ru.clevertec.newsapi.entity.comment;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.clevertec.newsapi.entity.news.News;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class for entity, which describes comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Unique identifier for comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_generator")
    @SequenceGenerator(name = "comments_generator", sequenceName = "seq_comment", allocationSize = 1)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    /**
     * Date and time, when comment was created.
     */
    @CreationTimestamp
    @Column(name = "comment_created_at")
    private LocalDateTime date;

    /**
     * Text, which describes comment.
     */
    @Column(name = "comment_text", nullable = false)
    private String text;

    /**
     * Author of comment.
     */
    @Column(name = "comment_author", nullable = false)
    private String username;

    /**
     * References to news identifier.
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "news_id")
    private News news;


}
