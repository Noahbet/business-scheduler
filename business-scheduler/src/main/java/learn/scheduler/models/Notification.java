package learn.scheduler.models;

import java.time.LocalDateTime;

public class Notification {

    private int notificationId;
    private int senderId;
    private String senderEmail;
    private int receiverId;
    private String receiverEmail;
    private String message;

    public Notification() {
    }

    public Notification(int notificationId, String senderEmail, int receiverId, String receiverEmail, String message) {
        this.notificationId = notificationId;
        this.senderEmail = senderEmail;
        this.receiverId = receiverId;
        this.receiverEmail = receiverEmail;
        this.message = message;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getMessage() {
        return message;
    }
}
