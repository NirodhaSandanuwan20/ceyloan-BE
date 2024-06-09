package com.exam.controller;


import com.exam.model.Notification;
import com.exam.model.User;
import com.exam.repo.NotificationRepository;
import com.exam.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/new")
    public List<Notification> getNewNotifications(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        return notificationService.getNewNotifications(user);
    }

    @GetMapping("/seen")
    public List<Notification> getSeenNotifications(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        return notificationService.getSeenNotifications(user);
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications(@RequestParam Long userId) {
        return notificationService.getAllNotifications(userId);
    }

    @PostMapping("/add")
    public void addNotification(@RequestParam Long userId, @RequestParam String message) {
        User user = new User();
        user.setId(userId);
        notificationService.addNotification(user, message);
    }

    @PostMapping("/mark-seen")
    public void markNotificationAsSeen(@RequestParam Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notificationService.markNotificationAsSeen(notification.get());
    }
}
