package com.xpring.edu.services;

import java.util.List;

import com.xpring.edu.model.Notification;
import com.xpring.edu.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void add(Notification notification) {
        notificationRepository.add(notification);
    }

    public void delete(int notificationID) {
        notificationRepository.delete(notificationID);
    }

    public void update(Notification notification) {
        notificationRepository.update(notification);
    }

    public List<Notification> getAll() {
        return notificationRepository.getAll();
    }

    public Notification get(int notificationID) {
        return notificationRepository.get(notificationID);
    }
}
