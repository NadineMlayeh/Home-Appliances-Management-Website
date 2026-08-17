package org.sid.electromenager.web;

import org.sid.electromenager.dao.NotificationRepository;
import org.sid.electromenager.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private NotificationRepository notificationRepository;

    @ModelAttribute
    public void addNotificationsToModel(Model model) {
        try {
            List<Notification> notifications = notificationRepository.findAll();
            if (notifications != null) {
                Collections.reverse(notifications);
                long unreadCount = notifications.stream().filter(n -> !n.isRead()).count();

                model.addAttribute("unreadCount", unreadCount);
                model.addAttribute("notifications", notifications);
            }
        } catch (Exception e) {
            // Fallback gracefully if database initialization in progress
        }
    }
}
