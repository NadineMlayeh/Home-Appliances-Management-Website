package org.sid.electromenager.dao;

import java.util.List;

import org.sid.electromenager.entities.Article;
import org.sid.electromenager.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByMessageContaining(String message);
	List<Notification> findByArticleId(Long articleId);
}

