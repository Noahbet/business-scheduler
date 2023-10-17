package learn.scheduler.models;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentId;
    private int customerId;
    private LocalDateTime appointmentDateTime;
    private Service service;

    public Appointment() {
    }

    public Appointment(int appointmentId, int customerId, LocalDateTime appointmentDateTime) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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
