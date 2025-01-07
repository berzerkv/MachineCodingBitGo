package service;

import model.Notification;
import model.NotificationStatus;
import model.NotificationType;

import java.util.*;

public class NotificationService {
    Map<NotificationType, List<String>> notificationMap;
    Map<NotificationStatus, List<String>> notificationStatusListMap;
    Map<String, Notification> notificationIdtoNotificationMap;

    // Store list of notifications

    public NotificationService(){
        notificationMap = new HashMap<>();
        notificationStatusListMap = new HashMap<>();
        notificationIdtoNotificationMap = new HashMap<>();
    }

    public String createNotification(NotificationType notificationType, Map<String, Object> params){
        String notificationId = UUID.randomUUID().toString();
        if(notificationMap.containsKey(notificationType)){
            notificationMap.get(notificationType).add(notificationId);
            notificationIdtoNotificationMap.put(notificationId, new Notification(notificationType, params, notificationId));
        }
        else{
            List<String> notifications = new ArrayList<>();
            notifications.add(notificationId);
            notificationMap.put(notificationType, notifications);
            notificationIdtoNotificationMap.put(notificationId, new Notification(notificationType, params, notificationId));
        }

        // trigger notification to all subscribed to notification type

        return notificationId;
    }


    public void sendNotification(NotificationType notificationType, String email){
        // send email to user
    }

    public List<Notification> listNotification(Optional<Boolean> status){



    }



}
