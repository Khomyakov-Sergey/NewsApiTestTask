package ru.clevertec.newsapi.repository.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.newsapi.entity.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByNews_Id(Long newsId, Pageable pageable);

}
