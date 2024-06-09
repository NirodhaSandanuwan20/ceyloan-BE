package com.exam.service.impl;

import com.exam.model.Notification;
import com.exam.model.User;
import com.exam.repo.NotificationRepository;
import com.exam.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNewNotifications(User user) {
        return notificationRepository.findByUserAndIsNewTrue(user);
    }

    public List<Notification> getSeenNotifications(User user) {
        return notificationRepository.findByUserAndIsNewFalse(user);
    }

    public List<Notification> getAllNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void addNotification(User user, String message) {
        if (notificationRepository.findByUser(user).size() >= 20) {
            List<Notification> oldestNotifications = notificationRepository.findOldestNotifications(user);
            notificationRepository.delete(oldestNotifications.get(0));
        }

        Notification notification = new Notification(message, true, LocalDateTime.now(), user);
        notificationRepository.save(notification);
    }

    public void markNotificationAsSeen(Notification notification) {
        notification.setNew(false);
        notificationRepository.save(notification);
    }
}
