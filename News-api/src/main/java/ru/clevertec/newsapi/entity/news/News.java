package ru.clevertec.newsapi.entity.news;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.clevertec.newsapi.entity.comment.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for entity, which describes news.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Entity
@Table(name = "news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    /**
     * Unique identifier for news.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_generator")
    @SequenceGenerator(name = "news_generator", sequenceName = "seq_news", allocationSize = 1)
    @Column(name = "news_id", nullable = false)
    private Long id;

    /**
     * Date and time, when news was created.
     */
    @CreationTimestamp
    @Column(name = "news_created_at")
    private LocalDateTime date;

    /**
     * Header, which describes main mind of news.
     */
    @Column(name = "news_title", nullable = false)
    private String title;

    /**
     * Text, which describes news.
     */
    @Column(name = "news_text", nullable = false, columnDefinition = "TEXT")
    private String text;

    /**
     * List of comments, for current news.
     */
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
