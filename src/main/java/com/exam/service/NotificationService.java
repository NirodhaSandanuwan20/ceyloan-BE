package com.exam.service;
import com.exam.model.Notification;
import com.exam.model.User;
import com.exam.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface NotificationService {

    public List<Notification> getNewNotifications(User user);
    public List<Notification> getSeenNotifications(User user);
    public List<Notification> getAllNotifications(Long userId);
    public void addNotification(User user, String message);
    public void markNotificationAsSeen(Notification notification);

}
