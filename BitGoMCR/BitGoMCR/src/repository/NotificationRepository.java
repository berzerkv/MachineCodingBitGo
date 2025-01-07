package repository;


import model.Notification;
import model.NotificationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepository {
    Map<String, Notification> notificationIdtoNotificationMap;

    public NotificationRepository(){
        this.notificationIdtoNotificationMap = new HashMap<>();
    }

    public void addNotification(String notificationId,Map<String,Object> params, NotificationType notificationType){
        notificationIdtoNotificationMap.put(notificationId, new Notification(notificationType, params, notificationId));
    }

    public List<Notification> getNotificationsFromIds(List<String> notificationIds){
        List<Notification> notifications = new ArrayList<>();
        for(String notId : notificationIds){
            notifications.add(notificationIdtoNotificationMap.get(notId));
        }
        return notifications;
    }

    public void deleteNotification(String notificationId){
        notificationIdtoNotificationMap.remove(notificationId);
    }
}

