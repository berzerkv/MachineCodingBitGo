package model;

import java.util.Map;

public class Notification {
    String notificationID;
    NotificationType notificationType;
    Map<String, Object> params;

    public Notification(NotificationType notificationType, Map<String, Object> params, String notificationID){
        this.notificationType = notificationType;
        this.params = params;
        this.notificationID = notificationID;
    }
}
