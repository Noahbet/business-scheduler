package learn.scheduler.models;

import javax.validation.constraints.*;

public class Notification {

    @PositiveOrZero
    private int notificationId;
    @NotNull
    @Positive
    private int senderId;
    @NotNull
    private String senderEmail;
    @NotNull
    @Positive
    private int receiverId;
    @NotNull
    private String receiverEmail;
    @NotNull
    private String message;

    public Notification(int notificationId, String senderEmail, int receiverId, String receiverEmail, String message) {
        this.notificationId = notificationId;
        this.senderEmail = senderEmail;
        this.receiverId = receiverId;
        this.receiverEmail = receiverEmail;
        this.message = message;
    }

    public Notification(int senderId, int receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
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
