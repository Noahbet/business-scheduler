package learn.scheduler.models;

import learn.scheduler.data.RatingJdbcTemplateRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class Business {

    @PositiveOrZero
    private int businessId;
    @NotBlank
    private String businessName;
    private Availability availability;
    @Positive
    private int ownerId;
    @NotNull
    private Category category;
    @PositiveOrZero
    private double rating;
    private List<Service> services;
    private List<Appointment> appointments;

    public Business() {
    }

    public Business(int businessId, String businessName, int ownerId, Category category) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.ownerId = ownerId;
        this.category = category;
    }

    public Business(String businessName, int ownerId, Category category) {
        this.businessName = businessName;
        this.ownerId = ownerId;
        this.category = category;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return businessId == business.businessId
                && businessName.equals(business.businessName)
                && ownerId == (business.ownerId)
                && category == (business.category);
    }
}
