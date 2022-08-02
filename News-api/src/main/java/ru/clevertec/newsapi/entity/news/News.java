package ru.clevertec.newsapi.entity.news;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.clevertec.newsapi.entity.comment.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "news_created_at")
    private LocalDateTime date;

    @Column(name = "news_title", nullable = false)
    private String title;

    @Column(name = "news_text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
