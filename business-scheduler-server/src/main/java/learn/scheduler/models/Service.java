package learn.scheduler.models;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Service {

    @PositiveOrZero
    private int serviceId;
    @Positive(message ="BusinessId should be a positive number.")
    private int businessId;
    @NotBlank(message ="ServiceName should not be blank.")
    private String serviceName;
    @Positive(message ="TotalTimeLength should be a positive number.")
    private int totalTimeLength;
    @Positive(message ="Cost should be a positive number.")
    private BigDecimal cost;

    public Service() {
    }

    public Service(int serviceId, int businessId, String serviceName, int totalTimeLength, BigDecimal cost) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.businessId = businessId;
        this.totalTimeLength = totalTimeLength;
        this.cost = cost;
    }

    public Service(String serviceName, int businessId, int totalTimeLength, BigDecimal cost) {
        this.serviceName = serviceName;
        this.businessId = businessId;
        this.totalTimeLength = totalTimeLength;
        this.cost = cost;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getTotalTimeLength() {
        return totalTimeLength;
    }

    public void setTotalTimeLength(int totalTimeLength) {
        this.totalTimeLength = totalTimeLength;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
