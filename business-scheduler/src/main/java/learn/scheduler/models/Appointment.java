package learn.scheduler.models;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentId;
    private int customerId;
    private int businessId;
    private LocalDateTime appointmentDateTime;
    private Service service;

    public Appointment(int appointmentId, int customerId, int businessId, LocalDateTime appointmentDateTime) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.businessId = businessId;
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

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
