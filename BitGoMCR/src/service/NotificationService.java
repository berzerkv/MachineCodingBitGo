package service;

import model.Notification;
import model.NotificationStatus;
import model.NotificationType;
import repository.NotificationRepository;

import java.util.*;

public class NotificationService {
    Map<NotificationType, List<String>> notificationMap;
    Map<NotificationStatus, List<String>> notificationStatusListMap;
    Map<String, Notification> notificationIdtoNotificationMap;
    NotificationRepository notificationRepository;

    public NotificationService(){
        notificationMap = new HashMap<>();
        notificationStatusListMap = new HashMap<>();
        notificationIdtoNotificationMap = new HashMap<>();
        notificationRepository = new NotificationRepository();
    }

    public String createNotification(NotificationType notificationType, Map<String, Object> params){
        String notificationId = UUID.randomUUID().toString();
        if(notificationMap.containsKey(notificationType)){
            notificationMap.get(notificationType).add(notificationId);
            notificationRepository.addNotification(notificationId, params, notificationType);
        }
        else{
            List<String> notifications = new ArrayList<>();
            notifications.add(notificationId);
            notificationMap.put(notificationType, notifications);
            notificationRepository.addNotification(notificationId, params, notificationType);
        }
        // trigger notification to all subscribed to notification type
        // We can encapsulate the fact about users subscribing to an event type. We can use the observer pattern
        // to send notifications to the users subscribed to the notification Type.
        return notificationId;
    }


    public void sendNotification(String notificationId, NotificationType notificationType, String email){
        // send email to user
        // if it was successful
        if(notificationStatusListMap.containsKey(NotificationStatus.SENT)){
            List<String> notificationIds = notificationStatusListMap.get(NotificationStatus.SENT);
            notificationIds.add(notificationId);
            notificationStatusListMap.put(NotificationStatus.SENT, notificationIds);
        }
        else{
            List<String> notificationIds = new ArrayList<>();
            notificationIds.add(notificationId);
            notificationStatusListMap.put(NotificationStatus.SENT, notificationIds);
        }

    }

    public List<Notification> listNotification(Optional<NotificationStatus> status){
        List<Notification> notifications = new ArrayList<>();
        if(status.isEmpty()){
            notifications.addAll(notificationRepository.getNotificationsFromIds(notificationStatusListMap.get(NotificationStatus.SENT)));
            notifications.addAll(notificationRepository.getNotificationsFromIds(notificationStatusListMap.get(NotificationStatus.OUTSTANDING)));
            notifications.addAll(notificationRepository.getNotificationsFromIds(notificationStatusListMap.get(NotificationStatus.FAILED)));
        }
        else{
            notifications.addAll(notificationRepository.getNotificationsFromIds(notificationStatusListMap.get(status.get())));
        }
        return notifications;
    }


    public void deleteNotification(String notificationId){
        notificationRepository.deleteNotification(notificationId);
    }

}
