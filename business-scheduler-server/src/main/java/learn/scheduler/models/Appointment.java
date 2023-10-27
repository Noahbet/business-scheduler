package learn.scheduler.models;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class Appointment {

    @PositiveOrZero
    private int appointmentId;
    @Positive(message ="CustomerId should be a positive number.")
    private int customerId;

    private String customerUser;
    @Positive(message ="BusinessId should be a positive number.")
    private int businessId;
    @Positive(message ="BusinessId should be a positive number.")
    private int serviceId;
    @NotNull(message ="AppointmentDateTime should not be null.")
    @FutureOrPresent(message ="AppointmentDateTime should not be in the past.")
    private LocalDateTime appointmentDateTime;

    public Appointment() {
    }

    public Appointment(int appointmentId, int customerId, int businessId,
                       int serviceId, LocalDateTime appointmentDateTime) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.businessId = businessId;
        this.serviceId = serviceId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment(int customerId, int businessId, int serviceId, LocalDateTime appointmentDateTime) {
        this.customerId = customerId;
        this.businessId = businessId;
        this.serviceId = serviceId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment(int appointmentId, int customerId, String customerUser, int businessId, int serviceId, LocalDateTime appointmentDateTime) {
        this.appointmentId = appointmentId;
        this.customerUser = customerUser;
        this.customerId = customerId;
        this.businessId = businessId;
        this.serviceId = serviceId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(String customerUser) {
        this.customerUser = customerUser;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
