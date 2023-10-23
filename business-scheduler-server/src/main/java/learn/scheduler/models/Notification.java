package learn.scheduler.models;

import javax.validation.constraints.*;

public class Notification {

    @PositiveOrZero
    private int notificationId;
    @Positive(message = "SenderId should be a positive number")
    private int senderId;
    @NotBlank(message = "SenderEmail should not be blank.")
    private String senderEmail;
    @Positive(message = "ReceiverId should be a positive number")
    private int receiverId;
    @NotBlank(message = "ReceiverEmail should not be blank.")
    private String receiverEmail;
    @NotBlank(message = "Message should not be blank.")
    private String message;

    public Notification(int senderId, String senderEmail, int receiverId, String receiverEmail, String message) {
        this.senderId = senderId;
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

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
