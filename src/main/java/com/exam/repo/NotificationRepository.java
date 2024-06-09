package com.exam.repo;

import com.exam.model.User;
import com.exam.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsNewTrue(User user);

    List<Notification> findByUserAndIsNewFalse(User user);

    List<Notification> findByUser(User user);

    @Query("SELECT n FROM Notification n WHERE n.user = ?1 ORDER BY n.timestamp ASC")
    List<Notification> findOldestNotifications(User user);

    List<Notification> findByUserId(Long userId);

}
