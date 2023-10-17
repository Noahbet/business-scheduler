package learn.scheduler.models;

import learn.scheduler.data.RatingJdbcTemplateRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Business {

    private int businessId;
    private String businessName;
    private List<Availability> availability;
    private int ownerId;
    private Category category;
    private double rating;
    private List<Service> services;
    private List<Appointment> appointments;
    private final RatingJdbcTemplateRepository ratingRepository = new RatingJdbcTemplateRepository(new JdbcTemplate());

    public Business() {
    }

    public Business(int businessId, String businessName, int ownerId, Category category) {
        this.businessId = businessId;
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

    public void setRating() {
        this.rating = ratingRepository.getAvgRatingById(businessId);
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
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
